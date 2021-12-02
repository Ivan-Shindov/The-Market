package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.dto.ViewUserItemsDTO;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.projection.UserWithoutItemsProjection;
import com.example.marketApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserEntity testUser;
    private UserServiceImpl userServiceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {
        userServiceToTest = new UserServiceImpl(mockUserRepository);
        ItemEntity item = new ItemEntity().setId(11L)
                .setName("Good item");

        ItemEntity item2 = new ItemEntity().setId(12L)
                .setName("Good item2");

        testUser = new UserEntity();
        testUser.setId(10L)
                .setUsername("ivan12")
                .setAccount(BigDecimal.valueOf(120))
                .setItems(List.of(item, item2));
    }

    @Test
    void testAddUserThrowExceptionThatThereIsSuchUser() {

        PostUserDto postUserDto = new PostUserDto()
                .setUsername("ivan12")
                .setAccount(BigDecimal.valueOf(120))
                .setId(10L);

        Mockito.when(mockUserRepository.findByUsername(postUserDto.getUsername()))
                .thenReturn(Optional.of(testUser));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userServiceToTest.addUser(postUserDto));
    }

    @Test
    void testAddUserWorkCorrectly() {

        PostUserDto postUserDto = new PostUserDto()
                .setUsername("ivan123")
                .setAccount(BigDecimal.valueOf(1200))
                .setId(11L);

        UserEntity userEntity = userServiceToTest.addUser(postUserDto);

        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals(postUserDto.getUsername(),userEntity.getUsername());
        Assertions.assertEquals(postUserDto.getAccount(),userEntity.getAccount());
    }

    @Test
    void getUserWithItemsByUserIdTest() {

        Mockito.when(mockUserRepository.findOwner(10L))
                .thenReturn(Optional.of(this.testUser));

        ViewUserDTO actual = userServiceToTest.getUserWithItemsByUserId(10L);

        List<String> testItems = this.testUser.getItems().stream()
                .map(ItemEntity::getName)
                .collect(Collectors.toList());

        List<String> actualItems = actual.getItems()
                .stream()
                .map(ViewUserItemsDTO::getName)
                .collect(Collectors.toList());

        Assertions.assertEquals(this.testUser.getUsername(),actual.getUsername());
        Assertions.assertEquals(this.testUser.getAccount(),actual.getAccount());
        Assertions.assertEquals(this.testUser.getId(),actual.getId());
        Assertions.assertIterableEquals(testItems, actualItems);
    }

    @Test
    void getUserWithItemsByUserIdThrowsException() {

       Assertions.assertThrows(
               IllegalArgumentException.class,
                        () -> userServiceToTest.getUserWithItemsByUserId(100L));

    }

    @Test
    void getUserWithoutItemsTest() {

       var userProj = new UserWithoutItemsProjection() {
          public Long getId() {
               return testUser.getId();
           }

          public String getUsername() {
              return testUser.getUsername();
          }

         public BigDecimal getAccount() {
              return testUser.getAccount();
           }
       };

        Mockito.when(mockUserRepository.getUserById(10L))
                .thenReturn(userProj);

        UserWithoutItemsProjection actual = this.userServiceToTest.getUserById(10L);

        Assertions.assertEquals(this.testUser.getId(),actual.getId());
        Assertions.assertEquals(this.testUser.getUsername(),actual.getUsername());
        Assertions.assertEquals(this.testUser.getAccount(),actual.getAccount());
    }

}
