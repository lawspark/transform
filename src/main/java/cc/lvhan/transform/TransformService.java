package cc.lvhan.transform;

import com.deepoove.poi.XWPFTemplate;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * The type Transform service.
 *
 * @author keveon
 */
@Service
@RequiredArgsConstructor
public class TransformService {

    private final DocumentConverter converter;

    /**
     * Render.
     *
     * @param in    the in
     * @param out   the out
     * @param model the model
     */
    @SneakyThrows
    public void render(InputStream in, OutputStream out, Map<String, String> model) {
        @Cleanup var template = XWPFTemplate.compile(in).render(model);
        template.write(out);
    }

    /**
     * Convert.
     *
     * @param in  the in
     * @param out the out
     */
    @SneakyThrows
    public void convert(InputStream in, OutputStream out) {
        var format = DefaultDocumentFormatRegistry.PDF;
        converter.convert(in).to(out).as(format).execute();
    }

    /**
     * Transform.
     *
     * @param in    the in
     * @param out   the out
     * @param model the model
     */
    @SneakyThrows
    public void transform(InputStream in, OutputStream out, Map<String, String> model) {
        @Cleanup var bos = new ByteArrayOutputStream();
        render(in, bos, model);
        @Cleanup var bis = new ByteArrayInputStream(bos.toByteArray());
        convert(bis, out);
    }
}
