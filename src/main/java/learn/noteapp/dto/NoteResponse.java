package learn.noteapp.dto;

import java.util.Date;

public record NoteResponse(String title, String body, Date created) {
}
