package cn.codethink.xiaoming.message.serializer;

import cn.codethink.xiaoming.expression.format.FormatConfiguration;
import cn.codethink.xiaoming.expression.format.PairedFormatUnit;
import cn.codethink.xiaoming.expression.format.SpacesFormatUnit;
import cn.codethink.xiaoming.expression.format.TextFormatUnit;
import com.google.common.base.Preconditions;

public class SerializingConfigurationImpl
    implements SerializingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean offline = false;
        private boolean storageResourcesBytes = false;
        private boolean explicitText = false;
        private FormatConfiguration formattingConfiguration = FormatConfiguration.getInstance();
        private PairedFormatUnit expressionBounds = PairedFormatUnit.of(TextFormatUnit.of("#{"), SpacesFormatUnit.empty(), TextFormatUnit.of("}"));
    
        @Override
        public Builder offline(boolean offline) {
            this.offline = offline;
            return this;
        }
    
        @Override
        public Builder storageResourcesBytes(boolean storageResourcesBytes) {
            this.storageResourcesBytes = storageResourcesBytes;
            return this;
        }
    
        @Override
        public Builder explicitText(boolean explicitText) {
            this.explicitText = explicitText;
            return this;
        }
    
        @Override
        public Builder formattingConfiguration(FormatConfiguration formattingConfiguration) {
            this.formattingConfiguration = formattingConfiguration;
            return this;
        }
    
        @Override
        public Builder expressionBounds(PairedFormatUnit expressionBounds) {
            this.expressionBounds = expressionBounds;
            return this;
        }
    
        @Override
        public SerializingConfiguration build() {
            return new SerializingConfigurationImpl(offline, storageResourcesBytes, explicitText,
                formattingConfiguration,
                expressionBounds);
        }
    }
    
    private static final SerializingConfiguration INSTANCE = SerializingConfiguration.builder().build();
    
    public static SerializingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean offline;
    private final Boolean storageResourcesBytes;
    private final boolean explicitText;
    private final FormatConfiguration formattingConfiguration;
    
    private final PairedFormatUnit expressionBounds;
    
    public SerializingConfigurationImpl(boolean offline,
                                        boolean storageResourcesBytes,
                                        boolean explicitText,
                                        FormatConfiguration formattingConfiguration,
                                        PairedFormatUnit expressionBounds) {
        
        Preconditions.checkNotNull(formattingConfiguration, "Formatting configuration is null!");
        Preconditions.checkNotNull(expressionBounds, "Expression bounds is null!");
        
        this.offline = offline;
        this.storageResourcesBytes = storageResourcesBytes;
        this.explicitText = explicitText;
        this.formattingConfiguration = formattingConfiguration;
        this.expressionBounds = expressionBounds;
    }
    
    public Boolean getStorageResourcesBytes() {
        return storageResourcesBytes;
    }
    
    @Override
    public boolean isOffline() {
        return offline;
    }
    
    @Override
    public boolean isStorageResourcesBytes() {
        return storageResourcesBytes;
    }
    
    @Override
    public PairedFormatUnit getExpressionBounds() {
        return expressionBounds;
    }
    
    @Override
    public boolean isExplicitText() {
        return explicitText;
    }
    
    @Override
    public FormatConfiguration getFormatConfiguration() {
        return formattingConfiguration;
    }
}
