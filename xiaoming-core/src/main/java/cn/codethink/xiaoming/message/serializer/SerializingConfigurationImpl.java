package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import com.google.common.base.Preconditions;

public class SerializingConfigurationImpl
    implements SerializingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean offline = false;
        private Boolean storageImageBytes = false;
        private boolean explicitText = false;
        private FormattingConfiguration formattingConfiguration = FormattingConfiguration.getInstance();
        private AnalyzingConfiguration analyzingConfiguration = AnalyzingConfiguration.getInstance();
        private int countOfSpacesBeforeExpression = 0;
        private int countOfSpacesAfterExpression = 0;
    
        @Override
        public Builder offline(boolean offline) {
            this.offline = offline;
            return this;
        }
    
        @Override
        public Builder storageImageBytes(boolean storageImageBytes) {
            this.storageImageBytes = storageImageBytes;
            return this;
        }
    
        @Override
        public Builder explicitText(boolean explicitText) {
            this.explicitText = explicitText;
            return this;
        }
    
        @Override
        public Builder formattingConfiguration(FormattingConfiguration formattingConfiguration) {
            this.formattingConfiguration = formattingConfiguration;
            return this;
        }
    
        @Override
        public Builder analyzingConfiguration(AnalyzingConfiguration analyzingConfiguration) {
            this.analyzingConfiguration = analyzingConfiguration;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeExpression(int countOfSpacesBeforeExpression) {
            this.countOfSpacesBeforeExpression = countOfSpacesBeforeExpression;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterExpression(int countOfSpacesAfterExpression) {
            this.countOfSpacesAfterExpression = countOfSpacesAfterExpression;
            return this;
        }
    
        @Override
        public SerializingConfiguration build() {
            return new SerializingConfigurationImpl(offline, storageImageBytes, explicitText,
                formattingConfiguration, analyzingConfiguration,
                countOfSpacesBeforeExpression, countOfSpacesAfterExpression);
        }
    }
    
    private static final SerializingConfiguration INSTANCE = SerializingConfiguration.builder().build();
    
    public static SerializingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean offline;
    private final Boolean storageImageBytes;
    private final boolean explicitText;
    private final FormattingConfiguration formattingConfiguration;
    private final AnalyzingConfiguration analyzingConfiguration;
    
    private final int countOfSpacesBeforeExpression;
    private final int countOfSpacesAfterExpression;
    
    public SerializingConfigurationImpl(boolean offline, Boolean storageImageBytes, boolean explicitText,
                                        FormattingConfiguration formattingConfiguration, AnalyzingConfiguration analyzingConfiguration,
                                        int countOfSpacesBeforeExpression, int countOfSpacesAfterExpression) {
        Preconditions.checkNotNull(formattingConfiguration, "Formatting configuration is null!");
        Preconditions.checkNotNull(analyzingConfiguration, "Analyzing configuration is null!");
    
        this.offline = offline;
        this.storageImageBytes = storageImageBytes;
        this.explicitText = explicitText;
        this.formattingConfiguration = formattingConfiguration;
        this.analyzingConfiguration = analyzingConfiguration;
        
        this.countOfSpacesBeforeExpression = countOfSpacesBeforeExpression;
        this.countOfSpacesAfterExpression = countOfSpacesAfterExpression;
    }
    
    public Boolean getStorageImageBytes() {
        return storageImageBytes;
    }
    
    @Override
    public int getCountOfSpacesBeforeExpression() {
        return countOfSpacesBeforeExpression;
    }
    
    @Override
    public int getCountOfSpacesAfterExpression() {
        return countOfSpacesAfterExpression;
    }
    
    @Override
    public boolean isOffline() {
        return offline;
    }
    
    @Override
    public Boolean isStorageImageBytes() {
        return storageImageBytes;
    }
    
    @Override
    public boolean isExplicitText() {
        return explicitText;
    }
    
    @Override
    public FormattingConfiguration getFormattingConfiguration() {
        return formattingConfiguration;
    }
    
    @Override
    public AnalyzingConfiguration getAnalyzingConfiguration() {
        return analyzingConfiguration;
    }
}
