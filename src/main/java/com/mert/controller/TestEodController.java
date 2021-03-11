package com.mert.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.KodeEod;
import com.mert.service.KodeEodService;
import com.mert.service.EodProgressService;
import com.mert.service.EodCalculationService;

@Controller
@RequestMapping("/testeod")
public class TestEodController {
	
	@Autowired
	private KodeEodService kodeEodService;
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@Autowired
	private EodCalculationService eodCalculationService;
	
	private List<KodeEod> _listKodeEod;
	
	private void InitiateCalculationProgress() {
		eodProgressService.deleteAll();
		
		for (KodeEod processEod : _listKodeEod) {
			eodProgressService.New(processEod.getKodeEod());
		}
	}
	
	@RequestMapping(value="/calculation", method = RequestMethod.GET)
	public ModelAndView Calculation(String errMsg, String sccMsg, String postBack) {
		ModelAndView modelAndView = new ModelAndView();
		
		_listKodeEod = kodeEodService.findAll();
		
		if ((postBack != null) && (postBack.equals("true"))) {
			
		}
		else {
			InitiateCalculationProgress();
		}
		
		modelAndView.addObject("listKodeEod", _listKodeEod);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("postBack", postBack);
		modelAndView.setViewName("testeod/calculation");
		return modelAndView;
	}
	
	@RequestMapping(value="/calculationrun/{kodeEod}", method = RequestMethod.POST)
	public ModelAndView CalculationRun(@PathVariable String kodeEod) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		String result = "";
		try {
			
			if (kodeEod.equals("1001")) {
				result = eodCalculationService.Calc1001();
			}
			else if (kodeEod.equals("1002")) {
				result = eodCalculationService.Calc1002();
			}
			else if (kodeEod.equals("1003")) {
				result = eodCalculationService.Calc1003();
			}
			else if (kodeEod.equals("1004")) {
				result = eodCalculationService.Calc1004();
			}
			else if (kodeEod.equals("1005")) {
				result = eodCalculationService.Calc1005();
			}
			else if (kodeEod.equals("1006")) {
				result = eodCalculationService.Calc1006();
			}
			else if (kodeEod.equals("1007")) {
				result = eodCalculationService.Calc1007();
			}
			else if (kodeEod.equals("1008")) {
				result = eodCalculationService.Calc1008();
			}
			else if (kodeEod.equals("1009")) {
				result = eodCalculationService.Calc1009();
			}
			else if (kodeEod.equals("1010")) {
				result = eodCalculationService.Calc1010();
			}
			else if (kodeEod.equals("1011")) {
				result = eodCalculationService.Calc1011();
			}
			else if (kodeEod.equals("1012")) {
				result = eodCalculationService.Calc1012();
			}
			else if (kodeEod.equals("1013")) {
				result = eodCalculationService.Calc1013();
			}
			else if (kodeEod.equals("1014")) {
				result = eodCalculationService.Calc1014();
			}
			else if (kodeEod.equals("1015")) {
				result = eodCalculationService.Calc1015();
			}
			else if (kodeEod.equals("1016")) {
				result = eodCalculationService.Calc1016();
			}
			
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/testeod/calculation?errMsg=" + e.getMessage() + "&postBack=true");
			return modelAndView;
		}
		
		String sccMsg = result;
		modelAndView.setViewName("redirect:/testeod/calculation?sccMsg=" + sccMsg + "&postBack=true");
		return modelAndView;
	}

}
