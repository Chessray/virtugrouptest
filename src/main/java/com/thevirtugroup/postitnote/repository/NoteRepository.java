package com.thevirtugroup.postitnote.repository;

import static com.thevirtugroup.postitnote.repository.UserRepository.DEFAULT_USER_ID;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.model.User;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {
  private final UserRepository userRepository;

  private final ListValuedMap<User, Note> userNotes;

  public NoteRepository(final UserRepository userRepository) {
    this.userRepository = userRepository;
    userNotes = new ArrayListValuedHashMap<>();
    fillUserNotes();
  }

  private void fillUserNotes() {
    final User defaultUser = userRepository.findById(DEFAULT_USER_ID);
    IntStream.range(1, 5)
             .mapToObj(noteId -> randomNote(noteId, defaultUser))
             .forEach(note -> userNotes.put(defaultUser, note));
  }

  private Note randomNote(final int noteId, final User user) {
    return new Note().withId(noteId)
                     .withUser(user)
                     .withContent(randomAlphanumeric(10, 42));
  }

  public List<Note> findAllNotesByUser(final User user) {
    return userNotes.get(user);
  }
}
