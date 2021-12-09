package item53;

public class Main {
    public static void main(String[] args) {




    }

    static int sum(int... args) {
        int sum = 0;
        for (int arg : args)
            sum += arg;
        return sum;
    }

    //안 좋은 방법
    static int min(int... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        int min = args[0];
        for (int i = 1; i < args.length; i++)
            if (args[i] < min)
                min = args[i];
        return min;
    }

    //좋은 방법
    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs)
            if (arg < min)
                min = arg;
        return min;
    }
}

