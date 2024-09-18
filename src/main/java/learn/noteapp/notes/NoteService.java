package learn.noteapp.notes;

import jakarta.persistence.EntityNotFoundException;
import learn.noteapp.auth.UserEntity;
import learn.noteapp.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public Long createNote(NoteRequest request, Authentication connectedUser){
        UserEntity user = (UserEntity) connectedUser.getPrincipal();
        Note note = Note.builder()
                .owner(user)
                .body(request.body())
                .title(request.title())
                .build();
        return repository.save(note).getId();
    }

    public NoteResponse findNoteById(Long id) {
        Note note = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Note not found"));
        return new NoteResponse(note.getTitle(), note.getBody(), Date.from(note.getCreatedOn()));
    }
}
