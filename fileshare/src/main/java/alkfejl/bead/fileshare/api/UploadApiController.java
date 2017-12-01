package alkfejl.bead.fileshare.api;

import alkfejl.bead.fileshare.api.UserApiController;
import alkfejl.bead.fileshare.model.Comment;
import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.service.CommentService;
import alkfejl.bead.fileshare.service.UploadService;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UploadApiController {

    @Autowired
    UploadService storageService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    UserApiController userApiController;

     @RequestMapping(value="listFiles/**", method = RequestMethod.GET)
      public ResponseEntity getListFiles(HttpServletRequest request) {
          String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
          restOfTheUrl = restOfTheUrl.replaceAll("/api/listFiles", "");
          try {
                  Iterable<File> f = storageService.findAllByPath(restOfTheUrl);
                  Iterable<Comment> c = commentService.listComments();
                  return ResponseEntity.ok(f);
          } catch (FileNotFoundException e) {
              return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
          } catch (Exception e) {
              return ResponseEntity.badRequest().build();
          }
      }

    @RequestMapping(value="showFile/**", method = RequestMethod.GET)
    public ResponseEntity<?> showFile(HttpServletRequest request) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/showFile", "");
        try {
            File file = storageService.showFile(restOfTheUrl);
            Iterable<Comment> c = commentService.listCommentsByFile(restOfTheUrl);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(c);
            return ResponseEntity.ok(json);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="listFiles/**/upload",method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        boolean success=false;
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/listFiles", "");
        restOfTheUrl = restOfTheUrl.substring(0, restOfTheUrl.length()-6);
        try {
            storageService.store(file, restOfTheUrl);
            return ResponseEntity.ok().build();
        } catch (UserNotValidException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not valid or is banned!");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (FileAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="listFiles/**/createDir",method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(HttpServletRequest request,@RequestParam("name") String name) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/listFiles", "");
        restOfTheUrl = restOfTheUrl.substring(0, restOfTheUrl.length()-9);
        try {
            storageService.store(restOfTheUrl, name);
            return ResponseEntity.ok().build();
        } catch (UserNotValidException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not valid or is banned!");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (FileAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/getFile/**", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getFile(HttpServletRequest request) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/getFile", "");
        Resource file = null;
        try {
            file = storageService.loadFile(restOfTheUrl);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        restOfTheUrl = restOfTheUrl.replaceAll(".*/", "");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + restOfTheUrl + "\"")
                .body(file);
    }


}