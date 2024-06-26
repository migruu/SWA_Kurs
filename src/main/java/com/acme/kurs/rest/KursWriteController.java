package com.acme.kurs.rest;

import com.acme.kurs.service.KursWriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.UUID;

import static com.acme.kurs.rest.KursGetController.ID_PATTERN;
import static com.acme.kurs.rest.KursGetController.REST_PATH;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Controller
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
class KursWriteController {

    private static final String PROBLEM_PATH = "/problem";
    private final KursWriteService service;
    private final KursMapper mapper;


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Einen neuen Kurs anlegen", tags = "Neuanlegen")
    @ApiResponse(responseCode = "201", description = "Kurs neu angelegt")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Email vorhanden")
    ResponseEntity<Void> post(@RequestBody final KursDTO kursDTO, final HttpServletRequest request) {
        log.debug("post {}", kursDTO);

        final var kursInput = mapper.toKurs(kursDTO);
        final var kurs = service.create(kursInput);
        final var location = URI.create("\"" + request.getRequestURL() + "/" + kurs.getId() + "\"");

        return created(location).build();
    }

    @PutMapping(path = "{id:" + ID_PATTERN + "}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Einen Kurs mit neuen Werten aktualisieren", tags = "Aktualisieren")
    @ApiResponse(responseCode = "204", description = "Aktualisiert")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "404", description = "Kurs nicht gefunden")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Kursnamen bereits vorhanden")
    void put(@PathVariable final UUID id, @RequestBody final KursDTO kursDTO) {
        log.debug("put: id={}, {}", id, kursDTO);

        final var kursInput = mapper.toKurs(kursDTO);
        service.update(kursInput, id);
    }

}
