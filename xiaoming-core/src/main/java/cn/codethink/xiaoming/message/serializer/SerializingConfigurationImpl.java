package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import com.google.common.base.Preconditions;

public class SerializingConfigurationImpl
    implements SerializingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean offline = false;
        private Boolean storageResourceBytes = false;
        private boolean explicitText = false;
        private FormattingConfiguration formattingConfiguration = FormattingConfiguration.getInstance();
        private int countOfSpacesBeforeExpression = 0;
        private int countOfSpacesAfterExpression = 0;
    
        @Override
        public Builder offline(boolean offline) {
            this.offline = offline;
            return this;
        }
    
        @Override
        public Builder storageResourceBytes(boolean storageResourceBytes) {
            this.storageResourceBytes = storageResourceBytes;
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
            return new SerializingConfigurationImpl(offline, storageResourceBytes, explicitText,
                formattingConfiguration,
                countOfSpacesBeforeExpression, countOfSpacesAfterExpression);
        }
    }
    
    private static final SerializingConfiguration INSTANCE = SerializingConfiguration.builder().build();
    
    public static SerializingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean offline;
    private final Boolean storageResourceBytes;
    private final boolean explicitText;
    private final FormattingConfiguration formattingConfiguration;
    
    private final int countOfSpacesBeforeExpression;
    private final int countOfSpacesAfterExpression;
    
    public SerializingConfigurationImpl(boolean offline, Boolean storageResourceBytes, boolean explicitText,
                                        FormattingConfiguration formattingConfiguration,
                                        int countOfSpacesBeforeExpression, int countOfSpacesAfterExpression) {
        Preconditions.checkNotNull(formattingConfiguration, "Formatting configuration is null!");
    
        this.offline = offline;
        this.storageResourceBytes = storageResourceBytes;
        this.explicitText = explicitText;
        this.formattingConfiguration = formattingConfiguration;
        
        this.countOfSpacesBeforeExpression = countOfSpacesBeforeExpression;
        this.countOfSpacesAfterExpression = countOfSpacesAfterExpression;
    }
    
    public Boolean getStorageResourceBytes() {
        return storageResourceBytes;
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
        return storageResourceBytes;
    }
    
    @Override
    public boolean isExplicitText() {
        return explicitText;
    }
    
    @Override
    public FormattingConfiguration getFormattingConfiguration() {
        return formattingConfiguration;
    }
}
