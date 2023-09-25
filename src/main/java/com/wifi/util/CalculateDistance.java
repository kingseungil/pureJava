package com.wifi.util;

public class CalculateDistance {

    public static double calculateDistance(double lat1, double lnt1, double lat2, double lnt2) {
        int R = 6371; // 지구의 반경(km)
        double latDistance = Math.toRadians(lat2 - lat1);
        double lntDistance = Math.toRadians(lnt2 - lnt1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
          + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
          * Math.sin(lntDistance / 2) * Math.sin(lntDistance / 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

}
