package ba.unsa.etf.rpr.tutorijal02;

import org.jetbrains.annotations.Contract;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean zaPocetna;
    private boolean zaKrajnja;


    Interval(double pocetna, double krajnja, boolean zaPocetna, boolean zaKrajnja) {
        if( krajnja < pocetna)
            throw new IllegalArgumentException("");

        this.pocetna = pocetna;
        this.krajnja = krajnja;
        this.zaPocetna = zaPocetna;
        this.zaKrajnja = zaKrajnja;
    }

    Interval() {
        pocetna = 0;
        krajnja = 0;
        zaKrajnja = false;
        zaPocetna = false;
    }


    public double getPocetna() {
        return pocetna;
    }

    public double getKrajnja() {
        return krajnja;
    }

    public boolean isZaPocetna() {
        return zaPocetna;
    }

    public boolean isZaKrajnja() {
        return zaKrajnja;
    }

    public boolean isNull() {

        if( getPocetna() != 0) return false;
        if( getKrajnja() != 0) return false;

        if( isZaPocetna()) return  false;
        if( isZaKrajnja()) return  false;

        return true;
    }
    public boolean isIn(double tacka) {
        if( tacka >= getPocetna() && tacka <= getKrajnja()) return true;
        return false;
    }

    public Interval intersect(Interval interval) {
        double a = getPocetna();
        double b = getKrajnja();
        double c = interval.getPocetna();
        double d = interval.getKrajnja();

        if( a < c && b < d && b < c || a > c && b > d && b > c && a > d)
            return new Interval();
        if( a > c && b < d )
            return new Interval(getPocetna(), getKrajnja(), isZaPocetna(), isZaKrajnja());
        if( c > a && d < b)
            return new Interval( interval.getPocetna(), interval.getKrajnja(), interval.isZaPocetna(), interval.isZaKrajnja());
        if( c < b && a < c && b < d)
            return new Interval(interval.getPocetna(), getKrajnja(), interval.isZaPocetna(), isZaKrajnja());
        if( a > c && a < d && b > d)
            return new Interval(getPocetna(), interval.getKrajnja(), isZaPocetna(), interval.isZaKrajnja());

        return new Interval();
    }
    public static Interval intersect(Interval prvi, Interval drugi) {
        double a = prvi.getPocetna();
        double b = prvi.getKrajnja();
        double c = drugi.getPocetna();
        double d = drugi.getKrajnja();

        if( a < c && b < d && b < c || a > c && b > d && b > c && a > d)
            return new Interval();
        if( a > c && b < d )
            return new Interval(prvi.getPocetna(), prvi.getKrajnja(), prvi.isZaPocetna(), prvi.isZaKrajnja());
        if( c > a && d < b)
            return new Interval( drugi.getPocetna(), drugi.getKrajnja(), drugi.isZaPocetna(), drugi.isZaKrajnja());
        if( c < b && a < c && b < d)
            return new Interval(drugi.getPocetna(), prvi.getKrajnja(), drugi.isZaPocetna(), prvi.isZaKrajnja());
        if( a > c && a < d && b > d)
            return new Interval(prvi.getPocetna(), drugi.getKrajnja(), prvi.isZaPocetna(), drugi.isZaKrajnja());
        return new Interval();

    }

    @Override
    public String toString() {
        String vratit = "";
        if( isZaPocetna()) vratit = vratit + "[";
        else vratit = vratit + "(";
        vratit = vratit + getPocetna() + "," + getKrajnja();
        if( isZaKrajnja()) vratit = vratit + "]";
        else vratit = vratit + ")";
        return vratit;
    }
    @Override
    public boolean equals(Object o) {
        Interval i = (Interval)o;
        if( isZaPocetna() != i.isZaPocetna()) return false;
        if( getPocetna() != i.getPocetna()) return false;
        if( getKrajnja() != i.getKrajnja()) return false;
        if( isZaKrajnja() != i.isZaKrajnja()) return false;
        return true;
    }



}
