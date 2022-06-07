package dol.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dol.example.domain.TCharacter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonUtil {

    /**
     * soap api 결과(xml)을 json객체로 변환
     * @param xml
     * @return
     */
    public JSONObject convertXmlToJson(String xml){
        JSONObject jsonObject = null;
        try{
            jsonObject = XML.toJSONObject(xml);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * json객체를 TCharacter로 변환
     * @param jsonObject
     * @return
     */
    public TCharacter convertJsonToTCharacter(JSONObject jsonObject) {
        jsonObject = getUserInfoFromNexonJson(jsonObject);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TCharacter tCharacter = null;
        try {
            tCharacter = objectMapper.readValue(jsonObject.toString(), TCharacter.class);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return tCharacter;
    }

    /**
     * soap api 결과(xml)를 json객체로 변환한 값에 대해 TCharacter로 변환하기 위한 값만 가져옴
     * @param jsonObject
     * @return
     */
    private JSONObject getUserInfoFromNexonJson(JSONObject jsonObject){
        return jsonObject
                .getJSONObject("soap:Envelope")
                .getJSONObject("soap:Body")
                .getJSONObject("GetCharacterInfoByAccountIDResponse")
                .getJSONObject("GetCharacterInfoByAccountIDResult")
                .getJSONObject("diffgr:diffgram")
                .getJSONObject("NewDataSet")
                .getJSONObject("UserInfo");
    }
}
