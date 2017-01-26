package com.baayso.springboot.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * @author ChenFangjie (2016/12/12 14:43)
 */
public class CommonController {

    public boolean validateJson(JsonNode schema, String json) {
        try {
            JsonNode data = JsonUtils.INSTANCE.getMapper().readTree(json);

            ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);

            return report.isSuccess();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
