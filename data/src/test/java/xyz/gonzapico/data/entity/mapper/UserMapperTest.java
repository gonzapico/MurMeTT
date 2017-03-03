package xyz.gonzapico.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import xyz.gonzapico.data.entity.Contact;
import xyz.gonzapico.data.entity.ResponseAPIUsers;
import xyz.gonzapico.domain.model.UserModelDomain;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by gfernandez on 3/03/17.
 */
public class UserMapperTest {
  private static final int FAKE_GENRE_ID = 111;
  private static final String FAKE_NAME = "Adventure";

  private UserMapper userMapper;

  @Before public void setUp() throws Exception {
    userMapper = new UserMapper();
  }
  @Test public void transformToListOfUsers() throws Exception {
    ResponseAPIUsers mockResponse = mock(ResponseAPIUsers.class);

    Contact contactOne = mock(Contact.class);
    Contact contactTwo = mock(Contact.class);
    List<Contact> contactList = new ArrayList<Contact>(2);
    contactList.add(contactOne);
    contactList.add(contactTwo);
    mockResponse.setContacts(contactList);

    Collection<UserModelDomain> userModelCollection = userMapper.transformToListOfUsers(Response.success(mockResponse));
    assertThat(userModelCollection.toArray()[0], is(instanceOf(UserModelDomain.class)));
    assertThat(userModelCollection.toArray()[1], is(instanceOf(UserModelDomain.class)));
    assertThat(userModelCollection.size(), is(2));

  }
}