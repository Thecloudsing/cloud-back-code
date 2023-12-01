import java.util.Random;

public class SqlTestData {
    private static Random random = new Random();

    public static void main(String[] args) {
        int count = 40;
        System.out.println((int) 'a' + "-" + (int) 'z');
        System.out.println((int) 'A' + "-" + (int) 'Z');
        System.out.println((int) '0' + "-" + (int) '9');
        System.out.println(5 /10);

//        for (int i = 0; i < 40; i++) {
//            String username = randomUsername(5);
//            String ap = randomAccount_Password(10);
//            print("('",username,"','",ap,"','",ap,"'),");
//        }

        for (int i = 0; i < 40; i++) {
            String username = randomUsername(5);
            String ap = randomAccount_Password(10);
            printf("('%s','%s','%s')", username, ap, ap);
        }
//        Integer a = 1;
//        Short b = 1;
//        Byte c = 1;
//        Long d = 1L;
//        Float f = 1f;
//        Double e = 1d;
//        Boolean g = true;
//        Character h = '1';
//        System.out.println(a.getClass().getSimpleName());
//        System.out.println(b.getClass().getSimpleName());
//        System.out.println(c.getClass().getSimpleName());
//        System.out.println(d.getClass().getSimpleName());
//        System.out.println(e.getClass().getSimpleName());
//        System.out.println(f.getClass().getSimpleName());
//        System.out.println(g.getClass().getSimpleName());
//        System.out.println(h.getClass().getSimpleName());
//        System.out.println(a.getClass().getTypeName());
//        System.out.println(a.getClass().getCanonicalName());
//        System.out.println(a.getClass().getName());
//        System.out.println(a.getClass().getSimpleName());
    }

    private static char geta_z() {
        return (char) (random.nextInt(26) + 97);
    }

    private static char getA_Z() {
        return (char) (random.nextInt(26) + 65);
    }

    private static char get0_9() {
        return (char) (random.nextInt(10) + 48);
    }

    private static char _switch_aA0(int m) {
        m &= 2;
        switch (m) {
            case 0:
                return geta_z();
            case 1:
                return getA_Z();
            case 2:
                return get0_9();
            default:
                return '0';
        }
    }

    private static String randomUsername(int len) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(_switch_aA0(random.nextInt(100)));
        }
        return stringBuffer.toString();
    }

    private static String randomAccount_Password(int len) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(get0_9());
        }
        return stringBuffer.toString();
    }

    private static void printf(String format, Object ...objs) {
        String[] type = {
                "Byte", //%d
                "Short", //%d
                "Integer", //%d
                "Long", //%d
                "Float", //%f
                "Double", //%f
                "Character", //%c
                "Boolean", //%b
                "String" // %s
        };
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = format.toCharArray();
        for (int i = 0, j = 0; i < chars.length; i++) {
            if (chars[i] == '%') {
                i++;
                examineType(chars[i],objs[j]);
                stringBuilder.append(objs[j]);
                j++;
            }
            else
                stringBuilder.append(chars[i]);
        }
        System.out.println(stringBuilder);
    }

    private static void print(String ...args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg);
        }
        System.out.println(stringBuilder);
    }

    private static void examineType(char c, Object o) {
        boolean _throw_ = false;
        if (o == null) throw new NullPointerException();
        switch (c) {
            case 'd' :
                if (!(
                        o instanceof Byte ||
                                o instanceof Short ||
                                o instanceof Integer ||
                                o instanceof Long
                ))
                    _throw_ = true;
                break;
            case 'f' :
                if (!(o instanceof Float || o instanceof Double))
                    _throw_ = true;
                break;
            case 'c' :
                if (!(o instanceof Character)) _throw_ = true;
                break;
            case 'b' :
                if (!(o instanceof Boolean)) _throw_ = true;
                break;
            case 's' :
                if (!(o instanceof String)) _throw_ = true;
                break;
            case 'o' :
                break;
            default:
                throw new RuntimeException("No type match found");
        }
        if (_throw_) throw new RuntimeException("The type do not match");
    }
}

enum Type {
    D("d","int"),
    C("c","char"),
    S("s", "String"),
    F("f", "double");
    final String key;
    final String ClassName;
    Type(String key, String ClassName) {
        this.key = key;
        this.ClassName = ClassName;
    }
}
