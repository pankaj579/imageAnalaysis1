package azure.ai.vision.imageanalysis.samples.imageResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionResult {
    @JsonProperty("text")
    public String getText() {
        return this.text; }
    public void setText(String text) {
        this.text = text; }
    String text;
    @JsonProperty("confidence")
    public String getConfidence() {
        return this.confidence; }
    public void setConfidence(String confidence) {
        this.confidence = confidence; }
    String confidence;
}
