package utils;

public class Formula {

    public Double riskCarcinogens(int type, double cA, double cH){
        double result;

        switch (type){
            case 1:
                result = ((cA * Ref.tOutKid * Ref.vOutKid + cH * Ref.tInKid * Ref.vInKid)
                        * Ref.EF * Ref.EDKid) / (Ref.BWKid * Ref.AT * 365);
                break;
            case 2:
                result = ((cA * Ref.tOutTeen * Ref.vOutTeen + cH * Ref.tInTeen * Ref.vInTeen)
                        * Ref.EF * Ref.EDTeen) / (Ref.BWTeen * Ref.AT * 365);
                break;
            case 3:
                result = ((cA * Ref.tOutAdults * Ref.vOutAdults + cH * Ref.tInAdults * Ref.vInAdult)
                        * Ref.EF * Ref.EDAdult) / (Ref.BWAdult * Ref.AT * 365);
                break;
            default:
                return null;
        }

        return result;
    }

    public Float riskNoncarcinogenic(){

        return null;
    }
}
