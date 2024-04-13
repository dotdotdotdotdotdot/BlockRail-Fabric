package net.olin.blockrail.util;

public class APISchema {


    private int tradeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(int inputAmount) {
        this.inputAmount = inputAmount;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(int outputAmount) {
        this.outputAmount = outputAmount;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    private String name;
    private String input;
    private int inputAmount;
    private String output;
    private int outputAmount;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    private String namespace;
    private String texturePath;
    public APISchema(int tradeId, String name, String input, int inputAmount, String output, int outputAmount, String namespace, String texturePath) {
        this.tradeId = tradeId;
        this.name = name;
        this.input = input;
        this.inputAmount = inputAmount;
        this.output = output;
        this.outputAmount = outputAmount;
        this.namespace = namespace;
        this.texturePath = texturePath;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }
}

