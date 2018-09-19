package cn.luv2code.sample.userprovider.utils.demo.utils;

import cn.luv2code.sample.userprovider.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hx
 * @Title: ExcelToEntity
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/9/1915:15
 */
public class ExcelToEntity {
    public static void main(String[] args) {
        String s = TxtDeal.main(new String[]{"user表.csv"});
        List<User> users = dealUserCVS(s);
        System.out.println();
    }

    private static List<User> dealUserCVS(String s) {
        List<User> users = new ArrayList<>();
        String[] strings = s.split("\n");
        for (int i = 1; i < strings.length ; i++) {
            User user = new User();
            String[] split = strings[i].split(",");
            user.setUsername(split[0]);
            user.setName(split[1]);
            try {
                user.setAge(Integer.valueOf(split[2]));
            }catch (Exception e){
                user.setAge(-1);
            }
            try {
                user.setBalance(BigDecimal.valueOf(Long.valueOf(split[3])));
            } catch (Exception e) {
                user.setBalance(new BigDecimal(-1));
            }
            users.add(user);
        }
        return users;
    }
}
