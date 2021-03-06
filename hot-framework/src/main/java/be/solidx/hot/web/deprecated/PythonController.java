package be.solidx.hot.web.deprecated;

/*
 * #%L
 * Hot
 * %%
 * Copyright (C) 2010 - 2016 Solidx
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.Writer;
import java.util.Map;

import javax.script.CompiledScript;

import org.python.core.PyDictionary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import be.solidx.hot.HotPageCompiler;
import be.solidx.hot.Script;
import be.solidx.hot.data.DB;

@Controller
public class PythonController extends ScriptExecutorController<CompiledScript, DB<PyDictionary>> {
	
	@Override
	@RequestMapping(value = "/{scriptName}.py",method = RequestMethod.GET)
	public ResponseEntity<String> handleScript(WebRequest webRequest, @PathVariable String scriptName) {
		return super.handleScript(webRequest, scriptName);
	}
	
	@Override
	@RequestMapping(value = "/{page}.hotp", method = RequestMethod.GET)
	public ResponseEntity<String> printHotPage(WebRequest webRequest, @PathVariable String page) {
		return super.printHotPage(webRequest, page);
	}
	
	@Override
	@RequestMapping(value = "/{script}.py",method = RequestMethod.POST)
	public String handleScriptPOST (WebRequest webRequest, @PathVariable String script, Writer writer) {
		return super.handleScriptPOST(webRequest, script, writer);
	}
	
	@Override
	protected String getScriptExtension() {
		return ".py";
	}

	@Override
	protected String getPageExtension() {
		return ".hotp";
	}
	
	public void setDbMap (Map<String, DB<PyDictionary>> dbMap) {
		this.dbMap = dbMap;
	}
	
	public void setPageCompiler(HotPageCompiler pageCompiler) {
		this.pageCompiler = pageCompiler;
	}
	
	public void setScriptExecutor(WebScriptExecutor<CompiledScript, DB<PyDictionary>> scriptExecutor) {
		this.scriptExecutor = scriptExecutor;
	}

	@Override
	protected Script<CompiledScript> buildScript(byte[] code, String name) {
		return new Script<CompiledScript>(code, name);
	}
}
