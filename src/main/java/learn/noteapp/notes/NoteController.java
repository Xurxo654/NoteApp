package learn.noteapp.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @PostMapping
    public ResponseEntity<Long> createNote(
            @RequestBody NoteRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.createNote(request, connectedUser));
    }
}
