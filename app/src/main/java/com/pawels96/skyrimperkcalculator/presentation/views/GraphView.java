package com.pawels96.skyrimperkcalculator.presentation.views;

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
import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.SkillType;
import com.pawels96.skyrimperkcalculator.domain.FPoint;
import com.pawels96.skyrimperkcalculator.domain.Perk;
import com.pawels96.skyrimperkcalculator.domain.Skill;
import com.pawels96.skyrimperkcalculator.domain.enums.ESpecialSkill;

import java.util.List;
import java.util.Map;

import static com.pawels96.skyrimperkcalculator.presentation.Utils.getPerkName;
import static com.pawels96.skyrimperkcalculator.domain.Perk.areNodesSelected;

public class GraphView extends View {

    private Paint selectedStealthPaint = new Paint();
    private Paint selectedCombatPaint = new Paint();
    private Paint selectedMagicPaint = new Paint();
    private Paint selectedVampirePaint = new Paint();
    private Paint selectedWerewolfPaint = new Paint();
    private Paint notSelectedPaint = new Paint();
    private Paint textPaint = new Paint();

    private static final int NODE_RADIUS = 15;

    private int TEXT_SIZE;

    private float w, h;
    private float currentX, currentY;
    private float clickedX, clickedY;

    private Context context;
    private Handler handler = new Handler();

    private Map<IPerk, FPoint> coordinates;
    private IPerk touchedPerk = null;
    private Skill skill;

    private GraphView.OnNodeClickedListener listener;
    private Rect textBounds = new Rect();

    public interface OnNodeClickedListener {
        void onNodeClicked(Perk perk);

        void onNodeHolding(Perk perk);
    }

    public Skill getSkill() {
        return skill;
    }

    public void setListener(GraphView.OnNodeClickedListener listener) {
        this.listener = listener;
    }

    public void display(Skill skill) {
        this.skill = skill;
        coordinates = skill.getCoordinates();
        invalidate();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TEXT_SIZE = spToPixels(context, 10);
        init();
    }

    private void init() {

        selectedStealthPaint.setColor(Color.GREEN);
        selectedStealthPaint.setStrokeWidth(5);
        selectedStealthPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedCombatPaint.setColor(Color.RED);
        selectedCombatPaint.setStrokeWidth(5);
        selectedCombatPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedMagicPaint.setColor(getResources().getColor(R.color.skillMagicBright));
        selectedMagicPaint.setStrokeWidth(5);
        selectedMagicPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedVampirePaint.setColor(getResources().getColor(R.color.skillVampireBright));
        selectedVampirePaint.setStrokeWidth(5);
        selectedVampirePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        selectedWerewolfPaint.setColor(getResources().getColor(R.color.skillWerewolfBright));
        selectedWerewolfPaint.setStrokeWidth(5);
        selectedWerewolfPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        notSelectedPaint.setColor(getResources().getColor(R.color.colorGray));
        notSelectedPaint.setStrokeWidth(5);
        notSelectedPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    public static int spToPixels(Context context, int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * scaledDensity);
    }

    private void drawEdge(Canvas canvas, Perk start, Perk end, Paint selectedPaint) {
        FPoint xy1 = coordinates.get(start.getPerk());
        FPoint xy2 = coordinates.get(end.getPerk());

        if (xy1 == null || xy2 == null) return;

        Paint paint = areNodesSelected(start, end) ? selectedPaint : notSelectedPaint;

        canvas.drawLine(xy1.getX() * w, xy1.getY() * h, xy2.getX() * w, xy2.getY() * h, paint);
    }

    private Paint getPaintForSkillType(SkillType type) {
        switch (type) {
            case STEALTH:
                return selectedStealthPaint;
            case COMBAT:
                return selectedCombatPaint;
            case MAGIC:
                return selectedMagicPaint;
        }

        if (skill.getIskill().equals(ESpecialSkill.SKILL_VAMPIRISM))
            return selectedVampirePaint;

        if (skill.getIskill().equals(ESpecialSkill.SKILL_LYCANTHROPY))
            return selectedWerewolfPaint;

        return null;
    }

    private void drawConnections(Canvas canvas, List<Perk> perks) {

        Paint selectedPaint = getPaintForSkillType(skill.getType());

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

        drawConnections(canvas, skill.getPerkList());
        drawNodes(canvas);
        drawLabels(canvas);
    }

    private void drawNodes(Canvas canvas) {

        Paint selectedPaint = getPaintForSkillType(skill.getType());

        for (IPerk perk : coordinates.keySet()) {

            if (coordinates.get(perk) != null) {

                Paint paint = notSelectedPaint;

                if (skill.get(perk) != null && skill.get(perk).isSelected())
                    paint = selectedPaint;

                canvas.drawCircle(coordinates.get(perk).getX() * w,
                        coordinates.get(perk).getY() * h,
                        NODE_RADIUS, paint);
            }
        }
    }

    private void drawLabels(Canvas canvas) {

        for (IPerk s : coordinates.keySet()) {

            String label = getPerkName(context, s);

            float x = coordinates.get(s).getX() * w;
            float y = coordinates.get(s).getY() * h;

            if (skill.get(s) != null && skill.get(s).isMultiState())
                label += skill.get(s).getStateAsString();

            textPaint.getTextBounds(label, 0, label.length(), textBounds);
            int textWidth = textBounds.width();

            int textX = (int) (x - textWidth / 2);

            if (textX < 0)
                textX = 0;
            else if (textX > w - textWidth)
                textX = (int) w - textWidth;

            float textY = y + NODE_RADIUS * 2f + textBounds.height() / 5;
            if (textY > h || textY < 0)
                textY = y;

            canvas.drawText(label, textX, textY, textPaint);
        }
    }

    private IPerk getClickedPerk(float x, float y) {

        if (coordinates == null)
            return null;

        int clickRadius = NODE_RADIUS * 2;

        for (IPerk perk : coordinates.keySet()) {
            FPoint point = coordinates.get(perk);

            if (x < point.getX() * w + clickRadius
                    && x > point.getX() * w - clickRadius
                    && y < point.getY() * h + clickRadius
                    && y > point.getY() * h - clickRadius)

                return perk;
        }
        return null;
    }

    private static boolean isHolding(float currentX, float currentY, float clickedX, float clickedY) {

        int radius = NODE_RADIUS * 5;

        return currentX < clickedX + radius
                && currentX > clickedX - radius
                && currentY < clickedY + radius
                && currentY > clickedY - radius;
    }

    private void onPerkClicked(IPerk perk) {
        listener.onNodeClicked(skill.get(perk));
    }

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

                currentX = event.getX();
                currentY = event.getY();

                touchedPerk = getClickedPerk(event.getX(), event.getY());

                handler.postDelayed(() -> {

                    if (touchedPerk != null) {

                        if (isHolding(currentX, currentY, clickedX, clickedY))
                            listener.onNodeHolding(skill.get(touchedPerk));
                        touchedPerk = null;
                    }
                }, 300);

                return true;

            case MotionEvent.ACTION_UP:

                if (touchedPerk != null) {
                    onPerkClicked(touchedPerk);
                    cancelHold();
                    touchedPerk = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void cancelHold() {
        handler.removeCallbacksAndMessages(null);
    }
}
