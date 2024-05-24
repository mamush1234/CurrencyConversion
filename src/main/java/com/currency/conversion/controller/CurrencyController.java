import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController public class CurrencyController
{ @PostMapping("/convertCurrency")
public CurrencyConversionResponse convertCurrency(@RequestBody CurrencyConversionRequest request)
{
    double exchangeRateINRtoGBP = 0.0096; //
    double convertedAmount = request.getAmount() * exchangeRateINRtoGBP;
    CurrencyConversionResponse response = new CurrencyConversionResponse();
    response.setCurrency(request.getDestCurrency());
    response.setConvertedAmount(convertedAmount);
    return response;
}
}