package com.pawels96.skyrimperkcalculator;

public class XmlConverter {

    public void doStuff(String args[]) {

        String[] names = new String[args.length];
        String[] descs = new String[args.length];

        String perkPrefix = perkSystem +"_p_" + skill +"_";
        String desPrefix = perkSystem +"_d_" + skill + "_";

        int separatorIndex = 0;

        for (int i = 0; i < args.length; i++){

            if (args[i].equals("des")){
                separatorIndex = i;
                break;
            }

            names[i] = args[i];
        }

        int j = 0;
        for (int i = separatorIndex + 1; i < args.length; i++){
            descs[j] = args[i];
            j++;
        }

        String descriptions = "";

        for (String s : descs)
            descriptions += s + " ";

        descs = descriptions.split("\\. ");

        for (String s : names){
            if (s != null)
                System.out.println(toXml(perkPrefix + toLower(s), s, false));
        }

        System.out.println();

        for (int i = 0; i < descs.length; i++){
            if (descs[i] != null && names[i] != null)
                System.out.println(toXml(desPrefix + toLower(names[i]), descs[i], true));
        }

        System.out.println();

        for (String s : names){
            if (s != null)
                System.out.println(toUpper(skill) + "_" + toUpper(s));
        }
    }

    private static String clean(String s){

        return s
                .replace(" ", "_")
                .replace("-", "_")
                .replace("'", "");
    }

    private static String toUpper(String s){
        String xml = s.toUpperCase();
        return clean(xml);
    }

    private static String toLower(String s){
        String xml = s.toLowerCase();
        return clean(xml);
    }

    private static String toXml(String name, String value, boolean dot){
        String xml =  "<string name=" + "\"" + name + "\">" + value;
        if (dot)
            xml += ".";
        return xml + "</string>";
    }

    private static String skill = "spc";
    private static String perkSystem = "vanilla";

}

