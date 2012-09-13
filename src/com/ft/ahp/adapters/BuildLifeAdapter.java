/**
 * 
 */
package com.ft.ahp.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.urbancode.anthill3.domain.buildlife.BuildLife;

/**
 * @author anurag.kapur
 *
 */
public class BuildLifeAdapter implements JsonSerializer<BuildLife>{

	@Override
	public JsonElement serialize(BuildLife buildLife, Type type,
			JsonSerializationContext jsc) {
		JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("buildLifeId", buildLife.getId());
        jsonObject.addProperty("latestStampValue", buildLife.getLatestStampValue());
        jsonObject.addProperty("actualWorkspaceDate", buildLife.getActualWorkspaceDate().toString());
        return jsonObject;
	}

}
