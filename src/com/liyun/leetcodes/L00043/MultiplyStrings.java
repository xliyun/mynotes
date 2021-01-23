package com.liyun.leetcodes.L00043;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-28 13:09
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
//        String num1= "123";
//        String num2 = "456";
//       String num1 = "123456789";
//       String num2 =  "987654321";
      String num1 =  "498828660196";
        String num2 = "840477629533";

        String multiply = multiplyStrings.multiply(num1, num2);
        System.out.println(multiply);
    }
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String sum = "0";
        /**
            1 2 3   num1
        乘  4 5 6   num2
        ----------
            7 3 8
          6 1 5
        4 9 2
         */
        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;//进位
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuffer temp = new StringBuffer();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10; //当前乘积的余数
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;//求进位
            }
            // 将当前结果与新计算的结果求和作为新的结果
            sum = addStrings(sum, temp.reverse().toString());
        }
        return sum;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuffer buffer = new StringBuffer();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            buffer.append(sum);
            carry = (x + y + carry) / 10;
        }
        return buffer.reverse().toString();
    }
}


