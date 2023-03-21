package com.thevirtugroup.postitnote.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import com.thevirtugroup.postitnote.repository.UserRepository;
import com.thevirtugroup.postitnote.security.SecurityContext;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController("/notes")
public class NoteController {
  private final UserRepository userRepository;
  private final NoteRepository noteRepository;

  public NoteController(final UserRepository userRepository, final NoteRepository noteRepository) {
    this.userRepository = userRepository;
    this.noteRepository = noteRepository;
  }

  @RequestMapping(method = GET)
  public List<Note> getNotes() {
    return noteRepository.findAllNotesByUser(userRepository.findById(
        SecurityContext.getLoggedInUser()
                       .getId()));
  }
}
