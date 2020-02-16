import java.util.*;

public class Solution3 {
    static Map<String, Integer> in = new HashMap<String, Integer>();

    static {
        in.put("*", 2);
        in.put("+", 1);
        in.put("-", 1);
        in.put("(", 0);
    }

    public int calculate(String s) {
        Stack<String> stack = new Stack<String>();
        List<String> result = new ArrayList<String>();
        s = s.replaceAll(" ", "");
        String num = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                num = putNumber(result, num);
                stack.push("(");
            } else if (in.keySet().contains(c + "")) {
                num = putNumber(result, num);
                String opt = c + "";
                while (!stack.empty() && in.get(opt) <= in.get(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(opt);
            } else if (c == ')') {
                num = putNumber(result, num);
                String rst = stack.pop();
                while (!rst.equals("(")) {
                    result.add(rst);
                    rst = stack.pop();
                }
            } else {
                num = num + c;
            }
        }
        if (!num.equals("")) {
            result.add(num);
        }

        while (!stack.empty()) {
            String rst = stack.pop();
            result.add(rst);
        }
        return Integer.parseInt(getResult(result).get(0));
    }

    private String putNumber(List<String> result, String num) {
        if (!num.equals("")) {
            result.add(num);
            num = "";
        }
        return num;
    }


    private List<String> getResult(List<String> result) {
        if (result.size() == 1) {
            return result;
        } else {
            List<String> rst = new ArrayList<String>();
            for (String str : result) {
                if (str.equals("+")) {
                    String num2 = rst.remove(rst.size() - 1);
                    String num1 = rst.remove(rst.size() - 1);
                    rst.add(Integer.parseInt(num1) + Integer.parseInt(num2) + "");
                } else if (str.equals("-")) {
                    String num2 = rst.remove(rst.size() - 1);
                    String num1 = rst.remove(rst.size() - 1);
                    rst.add(Integer.parseInt(num1) - Integer.parseInt(num2) + "");
                } else if (str.equals("*")) {
                    String num2 = rst.remove(rst.size() - 1);
                    String num1 = rst.remove(rst.size() - 1);
                    rst.add(Integer.parseInt(num1) * Integer.parseInt(num2) + "");
                } else {
                    rst.add(str);
                }
            }
            return rst;
        }
    }
}
