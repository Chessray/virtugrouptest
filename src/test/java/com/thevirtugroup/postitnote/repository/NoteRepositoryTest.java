package com.thevirtugroup.postitnote.repository;

import static com.thevirtugroup.postitnote.repository.UserRepository.DEFAULT_USER_ID;
import static org.assertj.core.api.BDDAssertions.then;

import com.thevirtugroup.postitnote.model.Note;
import java.util.List;
import org.junit.jupiter.api.Test;

class NoteRepositoryTest {
  private final UserRepository userRepository = new UserRepository();

  private final NoteRepository underTest = new NoteRepository(userRepository);

  @Test
  void shouldReturnListOfFourNotesForDefaultUser() {
    final List<Note> userNotes = underTest.findAllNotesByUser(userRepository.findById(
        DEFAULT_USER_ID));

    then(userNotes).isNotNull()
                   .hasSize(4);
  }
}