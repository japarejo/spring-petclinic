package org.springframework.samples.learning;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockObjectTests {

    // This will be a stub
    @Mock
    TradeRepository tradeRepository;

    // This will be a mock
    @Mock
    AuditService auditService;

    @Test
    public void testAuditLogEntryMadeForNewTrade() throws Exception {        
        when(tradeRepository.createTrade(any(Trade.class))).thenReturn(anyLong());

        TradingService tradingService = new LessSimpleTradingService(tradeRepository, auditService);
        Trade t=new Trade(1);
        tradingService.createTrade(t);

        verify(auditService).logNewTrade(t);
    }

}