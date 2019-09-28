package util.Generator;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.UserDAO;
import model.User;
import util.guice.PersistenceModule;

public class UserGenerator
{
private UserDAO userDAO;

    UserGenerator()
    {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        userDAO = injector.getInstance(UserDAO.class);
    }

    public void generateUser()
    {
        Faker faker = new Faker();
        String string = faker.name().name();
        String[] tempstring = string.split(" ");
        String email = tempstring[0] + "." + tempstring[1] + "@pajkaret.hu";
        User temp = User.builder()
                .id(faker.idNumber().validSvSeSsn())
                .address(faker.address().fullAddress())
                .birthDate(faker.date().birthday(12,122))
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .name(string)
                .email(email)
                .build();

        userDAO.persist(temp);
    }

    public void generateUsers(int count)
    {
        for(int i=0;i<count;i++)
        {
            generateUser();
        }
    }
}
