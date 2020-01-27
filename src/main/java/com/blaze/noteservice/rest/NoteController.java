package com.blaze.noteservice.rest;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Note Service Controller")
public interface NoteController {

    @ApiOperation(value = "Create a new notebook")
    @RequestMapping(value = "/notebook", produces = "application/json")
    ResponseEntity<String> createNotebook(@RequestParam("name") String name);

    @ApiOperation(value = "Create a new note within a notebook")
    @RequestMapping(value = "/note", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<String> createNote(@RequestParam("notebookId") int notebookId, @RequestBody Note note);

    @ApiOperation(value = "Update a note")
    @RequestMapping(value = "/note/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    ResponseEntity<String> updateNote(@PathVariable("id") int noteId, @RequestBody Note note);

    @ApiOperation(value = "Get a notebook (optionally, filter by a tag)")
    @RequestMapping(value = "/notebook/{id}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Notebook> getNotebook(@PathVariable("id") int notebookId, @RequestParam(value = "tag", required = false) String tag);

    @ApiOperation(value = "Get a note")
    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Note> getNote(@PathVariable("id") int noteId);

    @ApiOperation(value = "Delete a note")
    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Boolean> deleteNote(@PathVariable("id") int noteId);
}
