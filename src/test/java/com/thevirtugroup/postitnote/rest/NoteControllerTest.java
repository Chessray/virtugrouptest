package com.thevirtugroup.postitnote.rest;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.model.User;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import com.thevirtugroup.postitnote.repository.UserRepository;
import com.thevirtugroup.postitnote.security.SecurityContext;
import com.thevirtugroup.postitnote.security.SecurityUserWrapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {
  @Mock
  private NoteRepository noteRepository;
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private NoteController underTest;

  @Test
  void shouldReturnResponseFromRepository() {
    final User user =
        new User().withId(nextLong(1, 1000))
                  .withName(randomAlphanumeric(5, 15))
                  .withUsername(randomAlphanumeric(10, 24))
                  .withPassword(randomAlphanumeric(8, 64));
    final Note expectedNote = new Note().withId(nextLong(1001, 2000))
                                        .withContent(randomAlphanumeric(40, 400))
                                        .withUser(user);
    final SecurityUserWrapper securityUser = new SecurityUserWrapper(user.getUsername(),
        user.getPassword(), emptyList());
    securityUser.setId(user.getId());
    try (final MockedStatic<SecurityContext> securityContextMock =
             mockStatic(SecurityContext.class)) {
      securityContextMock.when(SecurityContext::getLoggedInUser)
                         .thenReturn(securityUser);
      given(userRepository.findById(user.getId())).willReturn(user);
      given(noteRepository.findAllNotesByUser(user)).willReturn(singletonList(expectedNote));

      final List<Note> actualNotes = underTest.getNotes();

      then(actualNotes).containsExactly(expectedNote);
    }
  }
}