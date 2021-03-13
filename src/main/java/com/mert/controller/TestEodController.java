package com.mert.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.KodeEod;
import com.mert.model.KodeTran;
import com.mert.service.EodTanggalService;
import com.mert.service.KodeEodService;
import com.mert.service.EodProgressService;
import com.mert.service.EodCalculationService;
import com.mert.service.EodPostingService;
import com.mert.service.KodeTranService;

@Controller
@RequestMapping("/testeod")
public class TestEodController {
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	@Autowired
	private KodeEodService kodeEodService;
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@Autowired
	private EodCalculationService eodCalculationService;
	
	@Autowired
	private EodPostingService eodPostingService;
	
	@Autowired
	private KodeTranService kodeTranService;
	
	
	
	private String _eodTanggal;
	
	private List<KodeEod> _listKodeEod;
	
	private List<KodeTran> _listKodeEodPosting;
	
	
	
	private void requestEodTanggal() throws Exception {
		Date today;
		today = new Date();
		
		try {
			today = eodTanggalService.getDate();
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = formatter.format(today);
		this._eodTanggal = strToday;
	}
	
	private boolean IsLastDayInMonth(String strEodDate) {
		boolean result = false;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date eodDate = formatter.parse(strEodDate);
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(eodDate);
	        Integer today = cal.get(Calendar.DAY_OF_MONTH);
	        Integer maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        
	        if (today == maxDay) {
	        	result = true;
	        }
		} catch(Exception e) {
			
		} 
        
        return result;
	}
	
	private void InitiateCalculationProgress() {
		eodProgressService.deleteAll();
		
		for (KodeEod processEod : _listKodeEod) {
			eodProgressService.New(processEod.getKodeEod());
		}
	}
	
	private void InitiatePostingProgress() {
		eodProgressService.deleteAll();
		
		for (KodeTran processEod : _listKodeEodPosting) {
			eodProgressService.New(processEod.getKoTran());
		}
	}
	
	
	
	@RequestMapping(value="/calculation", method = RequestMethod.GET)
	public ModelAndView Calculation(String errMsg, String sccMsg, String postBack) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		if (this.IsLastDayInMonth(_eodTanggal)) {
			_listKodeEod = kodeEodService.findAllEom();
		} else {
			_listKodeEod = kodeEodService.findAllEod();
		}
		
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
	
	
	
	@RequestMapping(value="/posting", method = RequestMethod.GET)
	public ModelAndView Posting(String errMsg, String sccMsg, String postBack) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		_listKodeEodPosting = kodeTranService.ListEodPosting();
		
		if ((postBack != null) && (postBack.equals("true"))) {
			
		}
		else {
			InitiatePostingProgress();
		}
		
		modelAndView.addObject("listKodeEodPosting", _listKodeEodPosting);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("postBack", postBack);
		modelAndView.setViewName("testeod/posting");
		return modelAndView;
	}
	
	@RequestMapping(value="/postingrun/{kodeTran}", method = RequestMethod.POST)
	public ModelAndView PostingRun(@PathVariable String kodeTran) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		String result = "";
		try {
			
			if (kodeTran.equals("4004")) {
				result = eodPostingService.Post4004();
			} else if (kodeTran.equals("4005")) {
				result = eodPostingService.Post4005();
			} else if (kodeTran.equals("4006")) {
				result = eodPostingService.Post4006();
			} else if (kodeTran.equals("4007")) {
				result = eodPostingService.Post4007();
			} else if (kodeTran.equals("4008")) {
				result = eodPostingService.Post4008();
			} else if (kodeTran.equals("4009")) {
				
			} else if (kodeTran.equals("4010")) {
				
			}
			
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/testeod/posting?errMsg=" + e.getMessage() + "&postBack=true");
			return modelAndView;
		}
		
		String sccMsg = result;
		modelAndView.setViewName("redirect:/testeod/posting?sccMsg=" + sccMsg + "&postBack=true");
		return modelAndView;
	}

}
