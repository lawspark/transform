package cc.lvhan.transform;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * The type Transform controller.
 *
 * @author keveon
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class TransformController {

    private final TransformService service;

    /**
     * Render response entity.
     *
     * @param resource the resource
     * @param model    the model
     * @return the response entity
     */
    @SneakyThrows
    @PostMapping("/render")
    public ResponseEntity<byte[]> render(@RequestParam("url") Resource resource, @RequestBody Map<String, String> model) {
        @Cleanup var in = resource.getInputStream();
        @Cleanup var out = new ByteArrayOutputStream();
        service.render(in, out, model);
        return ok(out.toByteArray());
    }

    /**
     * Convert response entity.
     *
     * @param resource the resource
     * @return the response entity
     */
    @SneakyThrows
    @PostMapping("/convert")
    public ResponseEntity<byte[]> convert(@RequestParam("url") Resource resource) {
        @Cleanup var in = resource.getInputStream();
        @Cleanup var out = new ByteArrayOutputStream();
        service.convert(in, out);
        return ok(out.toByteArray());
    }

    /**
     * Transform response entity.
     *
     * @param resource the resource
     * @param model    the model
     * @return the response entity
     */
    @SneakyThrows
    @PostMapping("/transform")
    public ResponseEntity<byte[]> transform(@RequestParam("url") Resource resource, @RequestBody Map<String, String> model) {
        @Cleanup var in = resource.getInputStream();
        @Cleanup var out = new ByteArrayOutputStream();
        service.transform(in, out, model);
        return ok(out.toByteArray());
    }
}
