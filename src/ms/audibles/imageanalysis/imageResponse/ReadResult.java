package ms.audibles.imageanalysis.imageResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadResult {
    @JsonProperty("stringIndexType")
    public String getStringIndexType() {
        return this.stringIndexType; }
    public void setStringIndexType(String stringIndexType) {
        this.stringIndexType = stringIndexType; }
    String stringIndexType;
    @JsonProperty("content")
    public String getContent() {
        return this.content; }
    public void setContent(String content) {
        this.content = content; }
    String content;
}
