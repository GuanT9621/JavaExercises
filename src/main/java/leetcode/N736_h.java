package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/parse-lisp-expression/
 * Lisp 语法解析
 * 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 * 表达式语法如下所示:
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 * let 表达式采用 "(let v1 e1 v2 e2 ... vn en expr)" 的形式，其中 let 总是以字符串 "let"来表示，接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量 v1被分配为表达式 e1 的值，第二个变量 v2 被分配为表达式 e2 的值，依次类推；最终 let 表达式的值为 expr表达式的值。
 * add 表达式表示为 "(add e1 e2)" ，其中 add 总是以字符串 "add" 来表示，该表达式总是包含两个表达式 e1、e2 ，最终结果是 e1 表达式的值与 e2 表达式的值之 和 。
 * mult 表达式表示为 "(mult e1 e2)" ，其中 mult 总是以字符串 "mult" 表示，该表达式总是包含两个表达式 e1、e2，最终结果是 e1 表达式的值与 e2 表达式的值之 积 。
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 *
 * 1 <= expression.length <= 2000
 * exprssion 中不含前导和尾随空格
 * expressoin 中的不同部分（token）之间用单个空格进行分隔
 * 答案和所有中间计算结果都符合 32-bit 整数范围
 * 测试用例中的表达式均为合法的且最终结果为整数
 *
 * 思路 哈希表 + 递归
 *
 * 思路 状态机
 *
 */
public class N736_h {

    class Solution {

        public int evaluate(String expression) {
            return evaluate(new HashMap<>(), expression);
        }

        private int evaluate(Map<String, Integer> vars, String s) {
            if (s.startsWith("(let")) return let(vars, toSs(s));
            if (s.startsWith("(add")) return add(vars, toSs(s));
            if (s.startsWith("(mult")) return mult(vars, toSs(s));
            if (vars.containsKey(s)) return vars.get(s);
            return Integer.parseInt(s);
        }

        private int let(Map<String, Integer> vars, String[] ss) {
            Map<String, Integer> temp = new HashMap<>(vars);
            int n = ss.length;
            for (int i = 1; i < n - 1; i += 2) {
                String v = ss[i];
                String e = ss[i + 1];
                temp.put(v, evaluate(temp, e));
            }
            return evaluate(temp, ss[n - 1]);
        }

        private int add(Map<String, Integer> vars, String[] ss) {
            Map<String, Integer> temp = new HashMap<>(vars);
            return evaluate(temp, ss[1]) + evaluate(temp, ss[2]);
        }

        private int mult(Map<String, Integer> vars, String[] ss) {
            Map<String, Integer> temp = new HashMap<>(vars);
            return evaluate(temp, ss[1]) * evaluate(temp, ss[2]);
        }
        // result is like: operate v1 v2 ... vn
        private String[] toSs(String expression) {
            expression = expression.substring(1, expression.length() - 1);
            List<String> ans = new ArrayList<>();
            StringBuilder temp = new StringBuilder();
            int count = 0;
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (' ' == c && count == 0) {
                    ans.add(temp.toString());
                    temp = new StringBuilder();
                } else {
                    temp.append(c);
                    count += '(' == c ? 1 : ')' == c ? -1 : 0;
                }
            }
            ans.add(temp.toString());
            return ans.toArray(new String[0]);
        }

    }

    public static void main(String[] args) {
        String expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))";
        int x = new N736_h().new Solution().evaluate(expression);
        System.out.println(x);
    }

}
