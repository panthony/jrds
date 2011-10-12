package jrds.configuration;

import java.lang.reflect.InvocationTargetException;

import jrds.Util;
import jrds.factories.xml.JrdsDocument;
import jrds.factories.xml.JrdsElement;
import jrds.objects.Filter;
import jrds.objects.FilterXml;

import org.apache.log4j.Logger;

public class FilterBuilder extends ConfigObjectBuilder<Filter> {

    static final private Logger logger = Logger.getLogger(FilterBuilder.class);

    public FilterBuilder() {
        super(ConfigType.FILTER);
    }

    @Override
	Filter build(JrdsDocument n) throws InvocationTargetException {
		try {
			return makeFilter(n);
		} catch (SecurityException e) {
			throw new InvocationTargetException(e, FilterBuilder.class.getName());
		} catch (IllegalArgumentException e) {
			throw new InvocationTargetException(e, FilterBuilder.class.getName());
		} catch (NoSuchMethodException e) {
			throw new InvocationTargetException(e, FilterBuilder.class.getName());
		} catch (IllegalAccessException e) {
			throw new InvocationTargetException(e, FilterBuilder.class.getName());
		} catch (InstantiationException e) {
            throw new InvocationTargetException(e, FilterBuilder.class.getName());
        }
	}

	public Filter makeFilter(JrdsDocument n) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
	    JrdsElement root = n.getRootElement();
	    FilterXml f = new FilterXml();
	    setMethod(root.getElementbyName("name"), f, "setName");
	    setMethod(root.getElementbyName("path"),f, "addPath");
	    setMethod(root.getElementbyName("tag"),f, "addTag");
	    setMethod(root.getElementbyName("qualifiedname"), f, "addGraph");
		doACL(f, n, root);
		logger.trace(Util.delayedFormatString("Filter loaded: %s", f.getName()));
		return f;
	}

}
