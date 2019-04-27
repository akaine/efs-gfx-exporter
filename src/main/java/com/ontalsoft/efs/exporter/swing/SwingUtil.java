package com.ontalsoft.efs.exporter.swing;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;

public class SwingUtil {

	public static Map<String,Component> createComponentMap(Container container){
		Map<String,Component> componentMap = new HashMap<String,Component>();
		componentMap.put(container.getName(), container);
		
	    List<Component> components = getAllComponents(container);
	    for(Component comp : components){
	        componentMap.put(comp.getName(), comp);
	    }
	    return componentMap;
	}
	
	@SuppressWarnings("unchecked")
	public static <ComponentType> ComponentType getComponentByName(Map<String,Component> componentMap, String name){
	    if(componentMap.containsKey(name)){
	        return (ComponentType)componentMap.get(name);
	    }else{
	    	return null;
	    }
	}
	
	private static List<Component> getAllComponents(final Container container) {
	    Component[] comps = container.getComponents();
	    if(container instanceof JMenu){
	    	JMenu jmenu = (JMenu)container;
	    	int size = jmenu.getItemCount();
	    	comps = new Component[size];
	    	for(int i=0; i<size; i++){
	    		comps[i] = jmenu.getItem(i);
	    	}
	    }else{
	    	comps = container.getComponents();
	    }
	    List<Component> compList = new ArrayList<Component>();
	    for(Component comp : comps){
	        compList.add(comp);
	        if(comp instanceof Container){
	            compList.addAll(getAllComponents((Container)comp));
	        }
	    }
	    return compList;
	}
}
