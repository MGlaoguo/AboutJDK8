package stream;

import stream.objects.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AboutStream {
    public static List<User> userList = new ArrayList<User>();
    public static Map<String,User> userMap = new HashMap<>();
    static {
        User user1 = new User("1001", 21,"zhang","beijing",180.5,70);
        User user2 = new User("2001", 22,"li","hangzhou",190.5,80);
        User user3 = new User("3001", 23,"wang","shanghai",200.5,90);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userMap.put("user1",user1);
        userMap.put("user2",user2);
        userMap.put("user3",user3);
    }
    public static void listExtractParam(){
//        userList.forEach(user-> System.out.println("name="+user.getName()));
        List<String> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        idList.forEach(id-> System.out.println("id="+id));
    }
    public static void listToMap(){
        Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        userMap.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
    public static void listByFilter(){
        userList = userList.stream().filter(User -> User.getId() .equals("1001")).collect(Collectors.toList());
        userList.forEach(user-> System.out.println("user="+user));
    }

    public static void main(String[] args) {
//        listExtractParam();
//        System.out.println("============");
//        listToMap();
        System.out.println("============");
        listByFilter();
    }
}
