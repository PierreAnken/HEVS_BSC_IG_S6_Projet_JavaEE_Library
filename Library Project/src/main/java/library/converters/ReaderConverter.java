package library.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import library.businessobject.Reader;
import library.libraryservice.LibraryService;

@FacesConverter(value = "readerConverter")
public class ReaderConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String readerId) {
		LibraryService libraryService = (LibraryService) context.getApplication().getExpressionFactory()
    			.createValueExpression(context.getELContext(), "#{LibraryService}", LibraryService.class);
    	
    	return libraryService.getReader(Long.parseLong(readerId));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object reader) {
		
		if(reader == null)
			return "";
		
		return Long.toString(((Reader)reader).getCardId())+"";
	}

}
