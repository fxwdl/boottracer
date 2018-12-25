package com.yida.boottracer.web.mgn.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.service.DictService;

@Controller
@RequestMapping(value = "/mgn/system")
public class DictRegionController
{
	@Autowired
	private DictService dictService;

	@GetMapping(value = { "dictRegion/GetChildren/{id}" })
	@ResponseBody
	public ResponseEntity<?> getChildren(@PathVariable("id") int id)
	{
		return new ResponseEntity<>(dictService.getRegionListByParentId(id), HttpStatus.OK);
	}
}
