package com.thevirtugroup.postitnote.model;

import java.util.Objects;

public class Note {
  private Long id;

  private String content;

  private User user;

  public Long getId() {
    return id;
  }

  public Note withId(final long id) {
    this.id = id;
    return this;
  }

  public String getContent() {
    return content;
  }

  public Note withContent(final String content) {
    this.content = content;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Note withUser(final User user) {
    this.user = user;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Note note = (Note) o;
    return Objects.equals(id, note.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
