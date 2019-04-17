package com.pawels96.skyrimperkcalculator.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.models.FPoint;
import com.pawels96.skyrimperkcalculator.models.Perk;
import com.pawels96.skyrimperkcalculator.models.Skill;

import java.util.HashMap;
import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.getPerkName;
import static com.pawels96.skyrimperkcalculator.models.Perk.areNodesSelected;
import static com.pawels96.skyrimperkcalculator.models.Skill.getCoordinates;

public class GraphView extends View {

    private Paint selectedStealthPaint;
    private Paint selectedCombatPaint;
    private Paint selectedMagicPaint;

    private Paint notSelectedPaint;
    private Paint textPaint;

    private float w, h;
    private static int nodeRadius = 15;
    private int textSize = 17;
    private Context context;

    private HashMap<IPerk, FPoint> coordinates;

    private Skill skill;
    private Handler handler;

    public interface OnNodeClickedListener {
        void onNodeClicked(Perk perk);

        void onNodePressed(Perk perk);
    }

    private PerkSystem system;

    public Skill getSkill() {
        return skill;
    }

    private GraphView.OnNodeClickedListener listener;
    private Rect textBounds = new Rect();

    public void setListener(GraphView.OnNodeClickedListener listener) {
        this.listener = listener;
    }

    public void setSkill(Skill skill, PerkSystem system) {
        this.skill = skill;
        this.system = system;

        coordinates = null;
        invalidate();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {

        selectedStealthPaint = new Paint();
        selectedStealthPaint.setColor(Color.GREEN);
        selectedStealthPaint.setStrokeWidth(5);
        selectedStealthPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedCombatPaint = new Paint();
        selectedCombatPaint.setColor(Color.RED);
        selectedCombatPaint.setStrokeWidth(5);
        selectedCombatPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedMagicPaint = new Paint();
        selectedMagicPaint.setColor(getResources().getColor(R.color.skillMagicBright));
        selectedMagicPaint.setStrokeWidth(5);
        selectedMagicPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        notSelectedPaint = new Paint();
        notSelectedPaint.setColor(getResources().getColor(R.color.colorGray));
        notSelectedPaint.setStrokeWidth(5);
        notSelectedPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        handler = new Handler();
    }

    private void drawEdge(Canvas canvas, Perk start, Perk end, Paint selectedPaint) {
        FPoint xy1 = coordinates.get(start.getPerk());
        FPoint xy2 = coordinates.get(end.getPerk());

        if (xy1 == null || xy2 == null) return;

        Paint paint = areNodesSelected(start, end) ? selectedPaint : notSelectedPaint;

        canvas.drawLine(xy1.x * w, xy1.y * h, xy2.x * w, xy2.y * h, paint);
    }

    private void drawConnections(Canvas canvas, List<Perk> perks) {

        Paint selectedPaint = selectedCombatPaint;

        switch (skill.getType()) {
            case STEALTH:
                selectedPaint = selectedStealthPaint;
                break;
            case COMBAT:
                selectedPaint = selectedCombatPaint;
                break;
            case MAGIC:
                selectedPaint = selectedMagicPaint;
                break;
        }

        for (Perk n : perks) {
            if (!n.hasChildren())
                continue;
            for (Perk n2 : n.children) {
                drawEdge(canvas, n, n2, selectedPaint);
            }
            drawConnections(canvas, n.children);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        w = getWidth();
        h = getHeight();

        if (skill == null) return;

        if (coordinates == null)
            coordinates = getCoordinates(skill.getSkillEnum(), system);

        drawConnections(canvas, skill.getChildrenList());
        drawNodes(canvas);
        drawLabels(canvas);

    }

    private void drawNodes(Canvas canvas) {

        Paint selectedPaint = selectedCombatPaint;

        switch (skill.getType()) {

            case MAGIC:
                selectedPaint = selectedMagicPaint;
                break;

            case COMBAT:
                selectedPaint = selectedCombatPaint;
                break;

            case STEALTH:
                selectedPaint = selectedStealthPaint;
                break;
        }

        for (IPerk perk : coordinates.keySet()) {

            if (coordinates.get(perk) != null) {

                Paint paint = notSelectedPaint;

                if (skill.get(perk) != null && skill.get(perk).isSelected())
                    paint = selectedPaint;

                canvas.drawCircle(coordinates.get(perk).x * w,
                        coordinates.get(perk).y * h,
                        nodeRadius, paint);
            }
        }
    }

    private void drawLabels(Canvas canvas) {

        for (IPerk s : coordinates.keySet()) {

            String label = getPerkName(context, s);

            float x = coordinates.get(s).x * w;
            float y = coordinates.get(s).y * h;

            if (skill.get(s) != null && skill.get(s).isMultiState())
                label += skill.get(s).getStateAsString();

            textPaint.getTextBounds(label, 0, label.length(), textBounds);
            int textWidth = textBounds.width();

            int textX = (int) (x - textWidth / 2);

            if (textX < 0)
                textX = 0;
            else if (textX > w - textWidth)
                textX = (int) w - textWidth;

            float textY = y + nodeRadius * 2f;
            if (textY > h || textY < 0)
                textY = y;

            canvas.drawText(label, textX, textY, textPaint);
        }
    }

    private IPerk getClickedPerk(float x, float y) {

        for (IPerk perk : coordinates.keySet()) {
            FPoint point = coordinates.get(perk);

            if (x < point.x * w + nodeRadius * 2
                    && x > point.x * w - nodeRadius * 2
                    && y < point.y * h + nodeRadius * 2
                    && y > point.y * h - nodeRadius * 2)

                return perk;

        }
        return null;
    }

    private static boolean isHolding(float currentX, float currentY, float clickedX, float clickedY) {
        return currentX < clickedX + nodeRadius * 2
                && currentX > clickedX - nodeRadius * 2
                && currentY < clickedY + nodeRadius * 2
                && currentY > clickedY - nodeRadius * 2;
    }

    private void onPerkClicked(IPerk perk) {

        skill.get(perk).nextState();

        invalidate();
        listener.onNodeClicked(skill.get(perk));
    }

    private IPerk touchedPerk = null;

    private float currentX, currentY;
    private float clickedX, clickedY;

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                return true;

            case MotionEvent.ACTION_DOWN:

                clickedX = event.getX();
                clickedY = event.getY();

                touchedPerk = getClickedPerk(event.getX(), event.getY());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (touchedPerk != null) {

                            if (isHolding(currentX, currentY, clickedX, clickedY))
                                listener.onNodePressed(skill.get(touchedPerk));
                            touchedPerk = null;
                        }
                    }

                }, 500);

                return true;

            case MotionEvent.ACTION_UP:

                if (touchedPerk != null) {
                    onPerkClicked(touchedPerk);
                    touchedPerk = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
