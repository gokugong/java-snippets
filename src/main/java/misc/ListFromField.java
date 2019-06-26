package misc;

import multithreading.ExecutorMisc;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFromField
{
    class Friend
    {
        String name;
        int id;

        public Friend(String name, int id)
        {
            this.name = name;
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public int getId() { return id; }

        @Override
        public String toString() { return this.getName() + ":" + this.getId(); }
    }

    class Person
    {
        String id;
        List<Friend> friends;

        public Person(String id, List<Friend> friends)
        {
            this.id = id;
            this.friends = friends;
        }

        public String getId()
        {
            return id;
        }

        public List<Friend> getFriends()
        {
            return friends;
        }

        @Override
        public String toString() { return this.getId() + "- " + this.getFriends(); }
    }

    public void makeListFromField()
    {
        List<Friend> friends1 = new ArrayList<>();
        friends1.add(new Friend("tom", 0));
        friends1.add(new Friend("tom2", 1));
        friends1.add(new Friend("tom3", 2));
        friends1.add(new Friend("tom3", 3));
        Person person1 = new Person("p1", friends1);

        List<Friend> friends2 = new ArrayList<>();
        friends2.add(new Friend("jason", 1));
        friends2.add(new Friend("jason2", 2));
        friends2.add(new Friend("jason3", 3));
        Person person2 = new Person("p1", friends2);

        List<Person> persons = Arrays.asList(person1, person2);

        List<ListFromField.Friend> allFriends = persons.stream().map(p -> p.getFriends()).flatMap(f -> f.stream()).collect(Collectors.toList());
        System.out.println(MessageFormat.format("All friends = {0}", allFriends));

        List<String> allFriendNames = persons.stream().map(p -> p.getFriends()).flatMap(f -> f.stream()).map(f -> f.getName()).collect(Collectors.toList());
        System.out.println(MessageFormat.format("All friend names = {0}", allFriendNames));

        List<String> allFriendFullNames = persons.stream().map(p -> p.getFriends()).flatMap(f -> f.stream()).map(f -> f.getName() + ":" + f.getId()).collect(Collectors.toList());
        System.out.println(MessageFormat.format("All friend names = {0}", allFriendFullNames));

        Set<String> nameSet = persons.stream().map(p -> p.getFriends()).flatMap(f -> f.stream()).map(f -> f.getName() + ":" + f.getId()).collect(Collectors.toSet());
        System.out.println(MessageFormat.format("All friend nameSet = {0}", nameSet));

        nameSet = persons.stream().map(p -> p.getFriends()).flatMap(f -> f.stream())
                .filter(f -> f.getName().startsWith("tom"))
                .map(f -> f.getName()).distinct().collect(Collectors.toSet());
        System.out.println(MessageFormat.format("All friend nameSet = {0}", nameSet));

        assert(nameSet.equals(allFriendFullNames.stream().collect(Collectors.toSet())));
    }

    public static void main(String[] args)
    {
        ListFromField lff = new ListFromField();
        lff.makeListFromField();
    }

}
