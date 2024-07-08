import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Prod {
    private String name; 
    private int pr, tdd;
    private ArrayList <String> cmnt = new ArrayList<String>();
    public Prod(String n, int p, int t) {
        name = n;
        pr = p;
        tdd = t;
    }
    public String getN() {
        return name;
    }
    public int getP() {
        return pr;
    }
    public int getT() {
        return tdd;
    }
    public void kam(int m) {
        tdd -= m;
    }
    public void addC(String c) {
        cmnt.add(c);
    }
    public void show() {
        for (String i: cmnt) {
            System.out.println(i);
        }
    }
    public void addT(int ad) {
        tdd += ad;
    }
}

class User {
    String id, pass;
    public void getInf(String i, String p) {
        id = i;
        pass = p;
    }
}

class Order {
    private ArrayList <Prod> sbd = new ArrayList<Prod>();
    private String mshtri, admn, vaziat = "loading", seller;
    public Order(ArrayList <Prod> arr, String m, String a, String sl) {
        sbd = arr;
        mshtri = m;
        admn = a;
        seller = sl;
    }
    public int hazine() {
        int h = 0;
        for (Prod i: sbd) {
            h += i.getP() * i.getT();
        }
        return h;
    }
    public String getS() {
        return seller;
    }
    public String getU() {
        return mshtri;
    }
    public String getA() {
        return admn;
    }
    public ArrayList<Prod> getB() {
        return sbd;
    }
    public void vaz(boolean fl) {
        if (fl == false)
            vaziat = "na";
        else
            vaziat = "are";
    }
    public String getV() {
        return vaziat;
    }
}

class Perm {
    private String req;
    private int money;
    private boolean ok = false;
    public Perm(String r, int m) {
        req = r;
        money = m;
    }
    public String getR() {
        return req;
    }
    public int getM() {
        return money;
    }
    public void kk() {
        ok = true;
    }
    public boolean getK() {
        return ok;
    }
    public void u(int kk) {
        money += kk;
    }
}

class Cust extends User {
    private String em, pn, adr;
    private int wal = 0;
    private ArrayList <Prod> sabad = new ArrayList<Prod>(), dara = new ArrayList<Prod>();
    private ArrayList <Order> sef = new ArrayList<Order>();
    public void getInf(String i, String p, String e, String pnn, String a) {
        id = i;
        pass = p;
        em = e;
        pn = pnn;
        adr = a;
    }
    public void addToSabad(Prod t) {
        sabad.add(t);
    }
    public ArrayList <Prod> getSabad() {
        return sabad;
    }
    public void sefaresh(Order ord) {
        sef.add(ord);
    }
    public void darayi(ArrayList<Prod> tsbd, int hzn) {
        for (Prod i: tsbd) {
            dara.add(i);
        }
        wal -= hzn;
    }
    public void addW(int ww) {
        wal += ww;
    }
}

class Slr extends User {
    private int earn = 0;
    public int sz;
    boolean[] vrf;
    public void ern(int e) {
        earn += e;
    }
    public void v(int a) {
        vrf = new boolean[a];
        for (int i = 0; i < a; i++)
            vrf[i] = false;
        sz = a;
    }
    public void hra(int p) {
        vrf[p] = true;
    }
}

class Adm extends User {
    private String em;
    boolean[] vr;
    private ArrayList <Order> q = new ArrayList<Order>();
    private ArrayList <Perm> que = new ArrayList<Perm>(), curr = new ArrayList<Perm>();
    public String getE() {
        return em;
    }
    public void getInf(String e) {
        em = e;
    }
    public void sefaresh(Order ord) {
        q.add(ord);
    }
    public void requ(Perm p) {
        que.add(p);
    }
    public void ver(int a) {
        vr = new boolean[a];
        for (int i = 0; i < a; i++)
            vr[i] = false;
    }
    public ArrayList<Order> ords() {
        ArrayList <Order> t = new ArrayList<Order>();
        Scanner scanner = new Scanner(System.in);
        for (Order i: q) {
            System.out.println("aya " + i.getU() + " ro taiid mikoni?");
            System.out.println("1.are");
            System.out.println("2.na");
            int sc = scanner.nextInt();
            int temp = 0;
            String msh = i.getU();
            for (Perm j: curr) {
                if (j.getR() == msh) {
                    temp = j.getM();
                    break;
                }
            }
            if (sc == 2 || (i.hazine() > temp)) {
                i.vaz(false);
                t.add(i);
            }
            else {
                i.vaz(true);
                t.add(i);
            }
        }
        return t;
    }
    public void addC(Perm p) {
        curr.add(p);
    }
    public ArrayList<Perm> prms() {
        Scanner scn = new Scanner(System.in);
        ArrayList <Perm> tmpr = new ArrayList<Perm>();
        for (Perm i: que) {
            System.out.println(i.getR() + ", " + i.getM() + " pool mikhad");
            System.out.println("1.are");
            System.out.println("2.na");
            int adad = scn.nextInt();
            if (adad == 1) {
                i.kk();
            }
            tmpr.add(i);
        }
        que.clear();
        return tmpr;
    }
    public void upd(String s1, int n1) {
        for (Perm i: curr) {
            if (i.getR() == s1) {
                i.u(n1);
                break;
            }
        }
    }
}

class Shop {
    private String name, addr, num;
    private ArrayList <Cust> cust = new ArrayList<Cust>();
    private ArrayList <Prod> prod = new ArrayList<Prod>();
    private ArrayList <Adm> adm = new ArrayList<Adm>(), nver = new ArrayList<Adm>();
    private ArrayList <Slr> slr = new ArrayList<Slr>(), feli = new ArrayList<Slr>();
    private int sood = 0;
    public Shop(String n, String a, String no, int m) {
        name = n;
        addr = a;
        num = no;
        sood = m;
        Adm frst = new Adm();
        frst.id = "first";
        frst.pass = "mypass";
        adm.add(frst);
    }
    public void catal() {
        int cnt = 1;
        for (Prod i: prod) {
            System.out.println(cnt + ".  " + i.getN() + "   " + i.getP() + "$   " + i.getT() + "ta");
            cnt++;
        }
    }
    public void loginC(String s) {
        Scanner inp = new Scanner(System.in);
        for (Cust i: cust) {
            if (i.id == s) {
                System.out.println("password:");
                String ss = inp.next();
                while (ss != i.pass) {
                    System.out.println("ramzeto dorost begu:");
                    ss = inp.next();
                }
                return;
            }
        }
        Cust tmp = new Cust();
        System.out.println("password:");
        String s1 = inp.next();
        System.out.println("email:");
        String s2 = inp.next();
        System.out.println("phone number:");
        String s3 = inp.next();
        System.out.println("address:");
        String s4 = inp.next();
        tmp.getInf(s, s1, s2, s3, s4);
        cust.add(tmp);
        Perm temp = new Perm(s, 0);
        for (Adm i: adm) {
            i.addC(temp);
        }
    }
    public void bag(String nam, int tedad, String esm) {
        for (Prod i: prod) {
            if (i.getN() == nam && i.getT() >= tedad) {
                Prod tmp = new Prod(nam, i.getP(), tedad);
                for (Cust j: cust) {
                    if (j.id == esm) {
                        j.addToSabad(tmp);
                        i.kam(tedad);
                        return;
                    }
                }
            }
        }
    }
    public void purchase(String usr) {
        Random rand = new Random();
        for (Cust i: cust) {
            if (i.id == usr) {
                int t = rand.nextInt(adm.size()), r = rand.nextInt(slr.size());
                Order tmp = new Order(i.getSabad(), usr, adm.get(t).id, slr.get(r).id);
                i.sefaresh(tmp);
                adm.get(t).sefaresh(tmp);
                return;
            }
        }
    }
    public void infr(String s1, String s2, String s3, String s4, String s5, String s6) {
        for (Cust i: cust) {
            if (i.id == s6) {
                i.getInf(s1, s2, s3, s4, s5);
                return;
            }
        }
    }
    public void darkhast(String adam, int pool) {
        Random rand = new Random();
        Perm tmp = new Perm(adam, pool);
        int r = rand.nextInt(adm.size());
        adm.get(r).requ(tmp);
    }
    public void addNazar(int nm, String cm) {
        prod.get(nm - 1).addC(cm);
    }
    public void showNazar(int shmre) {
        prod.get(shmre - 1).show();
    }
    public void verif(String s1, String s2) {
        Adm tmp = new Adm();
        tmp.id = s1;
        tmp.pass = s2;
        tmp.ver(adm.size());
        nver.add(tmp);
    }
    public void check(String s1) {
        int cnt = 0;
        for (Adm i: nver) {
            if (i.id == s1) {
                for (int j = 0; j < adm.size(); j++) {
                    if (i.vr[j] == false) {
                        System.out.println("taiid nashodi");
                        return;
                    }
                }
                System.out.println("taiid shodi!");
                adm.add(i);
                nver.remove(cnt);
                return;
            }
            cnt++;
        }
    }
    public boolean loginA(String s1, String s2) {
        for (Adm i: adm) {
            if (i.id == s1 && i.pass == s2)
                return true;
        }
        return false;
    }
    public void verOzv(String usm) {
        int ptr = 0;
        for (Adm i: adm) {
            if (i.id == usm)
                break;
            ptr++;
        }
        for (Adm i: nver) {
            Scanner scn = new Scanner(System.in);
            System.out.println("aya " + i.id + " ro taiid mikoni?");
            System.out.println("1.are");
            System.out.println("2.na");
            int sl = scn.nextInt();
            if (sl == 1)
                i.vr[ptr] = true;
        }
    }
    public void verOrd(String usm) {
        for (Adm i: adm) {
            if (i.id == usm) {
                ArrayList <Order> tt = new ArrayList<Order>();
                tt = i.ords();
                for (Order j: tt) {
                    ArrayList <Prod> ttt = new ArrayList<Prod>();
                    ttt = j.getB();
                    if (j.getV() == "na") {
                        for (Prod k: ttt) {
                            String esmesh = k.getN();
                            int tedadesh = k.getT();
                            for (Prod w: prod) {
                                if (esmesh == w.getN()) {
                                    w.addT(tedadesh);
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        int pardakht = j.hazine();
                        for (Cust k: cust) {
                            if (j.getU() == k.id) {
                                k.darayi(ttt, pardakht);
                                break;
                            }
                        }
                        sood += pardakht / 10;
                        pardakht -= pardakht / 10;
                        for (Slr k: slr) {
                            if (k.id == j.getS()) {
                                k.ern(pardakht);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void kpool(String usm) {
        for (Adm i: adm) {
            if (i.id == usm) {
                ArrayList<Perm> pms = new ArrayList<Perm>();
                pms = i.prms();
                for (Perm j: pms) {
                    if (j.getK() == true) {
                        for (Cust w: cust) {
                            if (w.id == j.getR()) {
                                w.addW(j.getM());
                                i.upd(w.id, j.getM());
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }
    public void newS() {
        Scanner eskan = new Scanner(System.in);
        System.out.println("username:");
        String s1 = eskan.next();
        System.out.println("password:");
        String s2 = eskan.next();
        Slr temp = new Slr();
        temp.id = s1;
        temp.pass = s2;
        feli.add(temp);
    }
    public void newF(String usm) {
        int ptr = 0;
        Scanner escn = new Scanner(System.in);
        for (Adm i: adm) {
            if (usm == i.id) {
                for (Slr j: feli) {
                    System.out.println("aya " + j.id + " ro taiid mikoni?");
                    System.out.println("1.are");
                    System.out.println("2.na");
                    int fff = escn.nextInt();
                    if (fff == 1) {
                        j.hra(ptr);
                    }
                }
            }
            ptr++;
        }
    }
    public void cS(String usm) {
        int pt = 0;
        for (Slr i: feli) {
            if (i.id == usm) {
                for (int j = 0; j < i.sz; j++) {
                    if (i.vrf[j] == false) {
                        System.out.println("na hanuz");
                        return;
                    }
                }
                System.out.println("taid shodi");
                slr.add(i);
                feli.remove(pt);
                break;
            }
            pt++;
        }
    }
}

public class project {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Shop myShop = new Shop("kijidala", "kijidala.sbu", "0095", 0);
        while (true) {
            System.out.println("salam ki hasti?");
            System.out.println("1.moshtari");
            System.out.println("2.modir");
            System.out.println("3.forooshande");
            System.out.println("4.hichi mikham beram");
            int n = input.nextInt();
            if (n == 1) {
                System.out.println("username:");
                String s = input.next();
                myShop.loginC(s);
                while (true) {
                    System.out.println("chikar mikhay bokoni?");
                    System.out.println("1.catalog ro bebinam");
                    System.out.println("2.ezafe be sabad");
                    System.out.println("3.kharid");
                    System.out.println("4.taghire moshakhasat");
                    System.out.println("5.ezafe kardane pool");
                    System.out.println("6.comment");
                    System.out.println("7.didane nazara");
                    System.out.println("8.khodafez");
                    int nn = input.nextInt();
                    if (nn == 1) {
                        myShop.catal();
                    }
                    else if (nn == 2) {
                        String nam = input.next();
                        int tedad = input.nextInt();
                        myShop.bag(nam, tedad, s);
                    }
                    else if (nn == 3) {
                        myShop.purchase(s);
                    }
                    else if (nn == 4) {
                        System.out.println("new username:");
                        String ss = input.next();
                        System.out.println("new password:");
                        String s1 = input.next();
                        System.out.println("new email:");
                        String s2 = input.next();
                        System.out.println("new phone number:");
                        String s3 = input.next();
                        System.out.println("new address:");
                        String s4 = input.next();
                        myShop.infr(ss, s1, s2, s3, s4, s);
                        s = ss;
                    }
                    else if (nn == 5) {
                        System.out.println("cheqad?");
                        int mq = input.nextInt();
                        myShop.darkhast(s, mq);
                    }
                    else if (nn == 6) {
                        System.out.println("mahsoole shomareye?");
                        int k = input.nextInt();
                        System.out.println("nazareto begoo:");
                        String nazar = input.next();
                        myShop.addNazar(k, nazar);
                    }
                    else if (nn == 7) {
                        System.out.println("kodoom mahsool?");
                        int tt = input.nextInt();
                        myShop.showNazar(tt);
                    }
                    else if (nn == 8) {
                        break;
                    }
                }
            }
            else if (n == 2) {
                while (true) {
                    System.out.println("mikhay chikar koni?");
                    System.out.println("1.ozv sham");
                    System.out.println("2.check konam");
                    System.out.println("3.vared sham");
                    System.out.println("4.khodafez");
                    int nn = input.nextInt();
                    if (nn == 1) {
                        System.out.println("username:");
                        String uname = input.next();
                        System.out.println("password:");
                        String pword = input.next();
                        myShop.verif(uname, pword);
                    }
                    else if (nn == 2) {
                        System.out.println("username:");
                        String un = input.next();
                        myShop.check(un);
                    }
                    else if (nn == 3) {
                        System.out.println("username:");
                        String unm = input.next();
                        System.out.println("password:");
                        String pwr = input.next();
                        if (myShop.loginA(unm, pwr) == false) {
                            System.out.println("nemishe");
                            continue;
                        }
                        System.out.println("vared shodi");
                        while (true) {
                            System.out.println("mikhay chikar koni?");
                            System.out.println("1.ozvaye jadido taiid konam");
                            System.out.println("2.sefaresh taiid konam");
                            System.out.println("3.forooshande taiid konam");
                            System.out.println("4.taiide pool");
                            System.out.println("5.back");
                            int mm = input.nextInt();
                            if (mm == 1) {
                                myShop.verOzv(unm);
                            }
                            else if (mm == 2) {
                                myShop.verOrd(unm);
                            }
                            else if (mm == 3) {
                                myShop.newF(unm);
                            }
                            else if (mm == 4) {
                                myShop.kpool(unm);
                            }
                            else if (mm == 5) {
                                break;
                            }
                        }
                    }
                    else if (nn == 4) {
                        break;
                    }
                }
            }
            else if (n == 3) {
                System.out.println("mikhay chikar koni?");
                System.out.println("1.sabte nam");
                System.out.println("2.check");
                System.out.println("3.bye");
                int mn = input.nextInt();
                if (mn == 1) {
                    myShop.newS();
                }
                else if (mn == 2) {
                    System.out.println("username:");
                    String sss = input.next();
                    myShop.cS(sss);
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }
    }
}   