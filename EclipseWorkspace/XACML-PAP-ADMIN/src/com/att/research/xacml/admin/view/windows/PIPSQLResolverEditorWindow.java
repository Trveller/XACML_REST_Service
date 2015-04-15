/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2014 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacml.admin.view.windows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeDesignatorType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.att.research.xacml.admin.jpa.Attribute;
import com.att.research.xacml.admin.jpa.PIPResolver;
import com.att.research.xacml.admin.jpa.PIPResolverParam;
import com.att.research.xacml.admin.util.AdminNotification;
import com.att.research.xacml.admin.util.XACMLConstants;
import com.att.research.xacml.admin.view.events.FormChangedEventListener;
import com.att.research.xacml.admin.view.events.FormChangedEventNotifier;
import com.att.research.xacml.api.XACML3;
import com.att.research.xacml.std.pip.engines.csv.ConfigurableCSVResolver;
import com.att.research.xacml.std.pip.engines.jdbc.ConfigurableJDBCResolver;
import com.att.research.xacml.std.pip.engines.ldap.ConfigurableLDAPResolver;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class PIPSQLResolverEditorWindow extends CustomComponent implements FormChangedEventNotifier, Handler {
	
	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Table tableAttributes;

	@AutoGenerated
	private Table tableRequiredAttributes;

	@AutoGenerated
	private CheckBox checkBoxShortIds;

	@AutoGenerated
	private TextField textFieldFilter;

	@AutoGenerated
	private TextField textFieldBase;

	@AutoGenerated
	private TextArea textAreaSelect;

	private final Action ADD_ATTRIBUTE = new Action("Add Attribute");
	private final Action EDIT_ATTRIBUTE = new Action("Edit Attribute");
	private final Action CLONE_ATTRIBUTE = new Action("Clone Attribute");
	private final Action REMOVE_ATTRIBUTE = new Action("Remove Attribute");
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	
	protected class ResolverAttribute implements Serializable {
		private static final long serialVersionUID = 1L;
		String	identifier = null;
		String	prefix = null;
		PIPResolverParam	id = null;
		PIPResolverParam	category = null;
		PIPResolverParam	datatype = null;
		PIPResolverParam	issuer = null;
		PIPResolverParam	column = null;
		
		public ResolverAttribute(String prefix, String identifier) {
			this.prefix = prefix;
			this.identifier = identifier;
		}
		
		public ResolverAttribute(String prefix, String identifier, ResolverAttribute target) {
			this(prefix, identifier);
			this.setId(target.getId());
			this.setCategory(target.getCategory());
			this.setDatatype(target.getDatatype());
			this.setIssuer(target.getIssuer());
			this.setColumn(target.getColumn());
			this.setResolver(target.id.getPipresolver());
		}

		public String	getIdentifier() {
			return this.identifier;
		}
		
		public String	getId() {
			if (this.id == null) {
				return null;
			}
			return this.id.getParamValue();
		}
		
		public String	getShortId() {
			String id = this.getId();
			if (id == null) {
				return null;
			}
			return XACMLConstants.extractShortName(id);
		}
		
		public PIPResolverParam getIdParam() {
			return this.id;
		}
		
		public void		setId(String id) {
			if (this.id == null) {
				this.id = new PIPResolverParam();
				this.id.setParamName(this.prefix + this.identifier + ".id");
			}
			this.id.setParamValue(id);
		}
		
		public void		setId(PIPResolverParam param) {
			this.id = param;
		}
		
		public String	getCategory() {
			if (this.category == null) {
				return null;
			}
			return this.category.getParamValue();
		}
		
		public String	getShortCategory() {
			String category = this.getCategory();
			if (category == null) {
				return null;
			}
			return XACMLConstants.extractShortName(category);
		}
		
		public PIPResolverParam	getCategoryParam() {
			return this.category;
		}
		
		public void		setCategory(String category) {
			if (this.category == null) {
				this.category = new PIPResolverParam();
				this.category.setParamName(this.prefix + this.identifier + ".category");
			}
			this.category.setParamValue(category);
		}
		
		public void setCategory(PIPResolverParam param) {
			this.category = param;
		}
		
		public String	getDatatype() {
			if (this.datatype == null) {
				return null;
			}
			return this.datatype.getParamValue();
		}
		
		public String	getShortDatatype() {
			String dt = this.getDatatype();
			if (dt == null) {
				return null;
			}
			return XACMLConstants.extractShortName(dt);
		}
		
		public PIPResolverParam getDatatypeParam() {
			return this.datatype;
		}
		
		public void		setDatatype(String datatype) {
			if (this.datatype == null) {
				this.datatype = new PIPResolverParam();
				this.datatype.setParamName(this.prefix + this.identifier + ".datatype");
			}
			this.datatype.setParamValue(datatype);
		}
		
		public void		setDatatype(PIPResolverParam param) {
			this.datatype = param;
		}
		
		public String	getIssuer() {
			if (this.issuer == null) {
				return null;
			}
			return this.issuer.getParamValue();
		}
		
		public String	getShortIssuer() {
			String issuer = this.getIssuer();
			if (issuer == null) {
				return null;
			}
			return XACMLConstants.extractShortName(issuer);
		}
		
		public PIPResolverParam getIssuerParam() {
			return this.issuer;
		}
		
		public void		setIssuer(String issuer) {
			if (this.issuer == null) {
				this.issuer = new PIPResolverParam();
				this.issuer.setParamName(this.prefix + this.identifier + ".issuer");
			}
			this.issuer.setParamValue(issuer);
		}

		public void		setIssuer(PIPResolverParam param) {
			this.issuer = param;
		}
		
		public Integer		getColumn() {
			if (this.column == null) {
				return null;
			}
			try {
				return Integer.parseInt(this.column.getParamValue());
			} catch (NumberFormatException e) {
				logger.error("Failed to set column: " + e);
				return null;
			}
		}
		
		public PIPResolverParam getColumnParam() {
			return this.column;
		}
		
		public void	setColumn(Integer col) {
			if (this.column == null) {
				this.column = new PIPResolverParam();
				this.column.setParamName(this.prefix + this.identifier + "column");
			}
			this.column.setParamValue(col.toString());
		}
		
		public void setColumn(PIPResolverParam param) {
			this.column = param;
		}

		public void setResolver(PIPResolver resolver) {
			if (this.id != null) {
				this.id.setPipresolver(resolver);
			}
			if (this.category != null) {
				this.category.setPipresolver(resolver);
			}
			if (this.datatype != null) {
				this.datatype.setPipresolver(resolver);
			}
			if (this.issuer != null) {
				this.issuer.setPipresolver(resolver);
			}
			if (this.column != null) {
				this.column.setPipresolver(resolver);
			}
		}
	}	
	
	private static final long serialVersionUID = 1L;
	private static final Log logger	= LogFactory.getLog(PIPSQLResolverEditorWindow.class);
	private final PIPSQLResolverEditorWindow self = this;
	private final EntityItem<PIPResolver> entity;
	private final BeanItemContainer<ResolverAttribute> fieldsContainer = new BeanItemContainer<ResolverAttribute>(ResolverAttribute.class);
	private final BeanItemContainer<ResolverAttribute> parametersContainer = new BeanItemContainer<ResolverAttribute>(ResolverAttribute.class);
	private final BasicNotifier notifier = new BasicNotifier();
	boolean isSaved = false;
	String fieldPrefix;
	String parameterPrefix;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public PIPSQLResolverEditorWindow(EntityItem<PIPResolver> entity) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		//
		// Save
		//
		this.entity = entity;
		//
		// Initialize
		//
		this.initialize();
	}
	
	protected boolean isSQL() {
		if (this.entity.getEntity().getPipconfiguration().getPiptype().isSQL() ||
			this.entity.getEntity().getPipconfiguration().getPiptype().isHyperCSV()) {
			return true;
		}
		return false;
	}
	
	protected boolean isLDAP() {
		return this.entity.getEntity().getPipconfiguration().getPiptype().isLDAP();
	}
	
	protected boolean isCSV() {
		return this.entity.getEntity().getPipconfiguration().getPiptype().isCSV();
	}
	
	protected void initialize() {
		//
		// Initialize entity
		//
		this.initializeEntity();
		//
		// Go through the parameters, save them into a
		// properties object.
		//
		boolean sourceInitialized = false;
		boolean attributeInitialized = false;
		for (PIPResolverParam param : this.entity.getEntity().getPipresolverParams()) {
			//
			// Look for ones we know about
			//
			if (param.getParamName().equalsIgnoreCase("select")) {
				this.textAreaSelect.setValue(param.getParamValue());
				this.textAreaSelect.setData(param);
			} else if (param.getParamName().equals("fields") || param.getParamName().equals("filter.view")) {
				this.initializeSourceTable(param.getParamValue());
				this.tableRequiredAttributes.setData(param);
				sourceInitialized = true;
			} else if (param.getParamName().equals("parameters") || param.getParamName().equals("filter.parameters")) {
				this.initializeAttributeTable(param.getParamValue());
				this.tableAttributes.setData(param);
				attributeInitialized = true;
			} else if (param.getParamName().equalsIgnoreCase("base")) {
				this.textFieldBase.setValue(param.getParamValue());
				this.textFieldBase.setData(param);
			} else if (param.getParamName().equalsIgnoreCase("filter")) {
				this.textFieldFilter.setValue(param.getParamValue());
				this.textFieldFilter.setData(param);
			}
		}
		//
		// Initialize GUI
		//
		this.initializeText();
		this.initializeCheckBox();
		//
		// Verify the tables get setup, if this is a new object
		// then they haven't been.
		//
		if (sourceInitialized == false) {
			this.initializeSourceTable("");
		}
		if (attributeInitialized == false) {
			this.initializeAttributeTable("");
		}
	}
	
	protected void initializeEntity() {
		//
		// Make sure the classname is set correctly
		//
		if (this.isSQL()) {
			//
			//
			//
			this.fieldPrefix = "field.";
			this.parameterPrefix = "parameter.";
			this.entity.getEntity().setClassname(ConfigurableJDBCResolver.class.getCanonicalName());
		} else if (this.isLDAP()) {
			//
			//
			//
			this.fieldPrefix = "filter.view.";
			this.parameterPrefix = "filter.parameters.";
			this.entity.getEntity().setClassname(ConfigurableLDAPResolver.class.getCanonicalName());
		} else if (this.isCSV()) {
			//
			//
			//
			this.fieldPrefix = "field.";
			this.parameterPrefix = "parameter.";
			this.entity.getEntity().setClassname(ConfigurableCSVResolver.class.getCanonicalName());
		}
	}
	
	protected void initializeText() {
		//
		// Are we SQL or LDAP?
		//
		if (this.isSQL()) {
			//
			// Turn these off
			//
			this.textFieldBase.setRequired(false);
			this.textFieldBase.setVisible(false);
			this.textFieldFilter.setRequired(false);
			this.textFieldFilter.setVisible(false);
			//
			// GUI properties
			//
			this.textAreaSelect.setImmediate(true);
			//
			// Respond to changes
			//
			this.textAreaSelect.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;
	
				@Override
				public void valueChange(ValueChangeEvent event) {
					PIPResolverParam param = (PIPResolverParam) self.textAreaSelect.getData();
					if (param == null) {
						param = new PIPResolverParam();
						param.setPipresolver(self.entity.getEntity());
						param.setParamName("select");
						self.entity.getEntity().addPipresolverParam(param);
						self.textAreaSelect.setData(param);
					}
					param.setParamValue(self.textAreaSelect.getValue());
					self.fireFormChangedEvent();
				}			
			});
		} else if (this.isLDAP()) {
			//
			// Turn these off
			//
			this.textAreaSelect.setRequired(false);
			this.textAreaSelect.setVisible(false);
			//
			//
			//
			this.textFieldBase.setImmediate(true);
			this.textFieldBase.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					PIPResolverParam param = (PIPResolverParam) self.textFieldBase.getData();
					if (param == null) {
						param = new PIPResolverParam();
						param.setPipresolver(self.entity.getEntity());
						param.setParamName("base");
						self.entity.getEntity().addPipresolverParam(param);
						self.textFieldBase.setData(param);
					}
					param.setParamValue(self.textFieldBase.getValue());
					self.fireFormChangedEvent();
				}			
			});
			//
			//
			//
			this.textFieldFilter.setImmediate(true);
			this.textFieldFilter.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					PIPResolverParam param = (PIPResolverParam) self.textFieldFilter.getData();
					if (param == null) {
						param = new PIPResolverParam();
						param.setPipresolver(self.entity.getEntity());
						param.setParamName("filter");
						self.entity.getEntity().addPipresolverParam(param);
						self.textFieldFilter.setData(param);
					}
					param.setParamValue(self.textFieldFilter.getValue());
					self.fireFormChangedEvent();
				}			
			});
		} else if (this.isCSV()) {
			//
			// Turn these off
			//
			this.textAreaSelect.setRequired(false);
			this.textAreaSelect.setVisible(false);
			this.textFieldBase.setRequired(false);
			this.textFieldBase.setVisible(false);
			this.textFieldFilter.setRequired(false);
			this.textFieldFilter.setVisible(false);
		}
	}
	
	protected void initializeCheckBox() {
		this.checkBoxShortIds.setValue(true);
		this.checkBoxShortIds.setImmediate(true);
		this.checkBoxShortIds.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (self.checkBoxShortIds.getValue()) {
					self.tableRequiredAttributes.setColumnCollapsed("id", true);
					self.tableRequiredAttributes.setColumnCollapsed("category", true);
					self.tableRequiredAttributes.setColumnCollapsed("datatype", true);
					self.tableRequiredAttributes.setColumnCollapsed("shortId", false);
					self.tableRequiredAttributes.setColumnCollapsed("shortCategory", false);
					self.tableRequiredAttributes.setColumnCollapsed("shortDatatype", false);
				} else {
					self.tableRequiredAttributes.setColumnCollapsed("id", false);
					self.tableRequiredAttributes.setColumnCollapsed("category", false);
					self.tableRequiredAttributes.setColumnCollapsed("datatype", false);
					self.tableRequiredAttributes.setColumnCollapsed("shortId", true);
					self.tableRequiredAttributes.setColumnCollapsed("shortCategory", true);
					self.tableRequiredAttributes.setColumnCollapsed("shortDatatype", true);
				}
			}			
		});
	}
	
	protected void initializeSourceTable(String fields) {
		//
		// Add data into the container
		//
		this.populateData(this.fieldPrefix, fields, this.fieldsContainer);
		//
		// Set GUI properties
		//
		this.tableRequiredAttributes.setContainerDataSource(this.fieldsContainer);
		this.tableRequiredAttributes.setPageLength((this.fieldsContainer.size() == 0 ? 3 : this.fieldsContainer.size() + 1));
		this.tableRequiredAttributes.setSizeFull();
		this.tableRequiredAttributes.setColumnCollapsingAllowed(true);
		this.tableRequiredAttributes.setVisibleColumns(new Object[] {"identifier", "id", "category", "datatype", "shortId", "shortCategory", "shortDatatype"});
		this.tableRequiredAttributes.setColumnHeaders(new String[] {"Field", "Attribute Id", "Category", "Data Type", "Attribute Id", "Category", "Data Type"});
		this.tableRequiredAttributes.setColumnCollapsed("id", true);
		this.tableRequiredAttributes.setColumnCollapsed("category", true);
		this.tableRequiredAttributes.setColumnCollapsed("datatype", true);
		this.tableRequiredAttributes.setSelectable(true);
		//
		// Setup its handler
		//
		this.tableRequiredAttributes.addActionHandler(this);
		//
		// Respond to events
		//
		this.tableRequiredAttributes.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					Object id = event.getItemId();
					if (id == null) {
						//
						// Really shouldn't get here
						//
						return;
					}
					BeanItem<ResolverAttribute> beanItem = self.fieldsContainer.getItem(id);
					if (beanItem == null) {
						//
						// Again, shouldn't get here
						//
						return;
					}
					self.editAttribute(self.tableRequiredAttributes, beanItem.getBean());
				}
			}
		});
	}
	
	protected void initializeAttributeTable(String parameters) {
		//
		// Add data into the container
		//
		this.populateData(this.parameterPrefix, parameters, this.parametersContainer);
		//
		// setup gui properties
		//
		this.tableAttributes.setContainerDataSource(this.parametersContainer);
		this.tableAttributes.setPageLength(this.parametersContainer.size() + 1);
		this.tableAttributes.setSizeFull();
		this.tableAttributes.setColumnCollapsingAllowed(true);
		this.tableAttributes.setVisibleColumns(new Object[] {"identifier", "id", "category", "datatype", "shortId", "shortCategory", "shortDatatype"});
		this.tableAttributes.setColumnHeaders(new String[] {"Position", "Attribute Id", "Category", "Data Type", "Attribute Id", "Category", "Data Type"});
		this.tableAttributes.setColumnCollapsed("id", true);
		this.tableAttributes.setColumnCollapsed("category", true);
		this.tableAttributes.setColumnCollapsed("datatype", true);
		this.tableAttributes.setSelectable(true);
		//
		// Setup its handler
		//
		this.tableAttributes.addActionHandler(this);
		//
		// Respond to events
		//
		this.tableAttributes.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					Object id = event.getItemId();
					if (id == null) {
						//
						// Really shouldn't get here
						//
						return;
					}
					BeanItem<ResolverAttribute> beanItem = self.parametersContainer.getItem(id);
					if (beanItem == null) {
						//
						// Again, shouldn't get here
						//
						return;
					}
					self.editAttribute(self.tableAttributes, beanItem.getBean());
				}
			}
		});
	}
	
	protected void populateData(String prefix, String list, BeanItemContainer<ResolverAttribute> container) {
		for (String field : Splitter.on(',').trimResults().omitEmptyStrings().split(list)) {
			//
			// Create a bean for this field
			//
			ResolverAttribute bean = new ResolverAttribute(prefix, field);
			//
			// Now try to find the attribute information
			//
			for (PIPResolverParam param : this.entity.getEntity().getPipresolverParams()) {
				if (param.getParamName().equals(prefix + field + ".id")) {
					bean.setId(param);
				} else if (param.getParamName().equals(prefix + field + ".category")) {
					bean.setCategory(param);
				} else if (param.getParamName().equals(prefix + field + ".datatype")) {
					bean.setDatatype(param);
				} else if (param.getParamName().equals(prefix + field + ".issuer")) {
					bean.setIssuer(param);
				} else if (param.getParamName().equals(prefix + field + ".column")) {
					bean.setColumn(param);
				}
			}
			container.addBean(bean);
		}
	}

	@Override
	public Action[] getActions(Object target, Object sender) {
		if (target == null) {
			return new Action[] {ADD_ATTRIBUTE};
		}
		return new Action[] {EDIT_ATTRIBUTE, CLONE_ATTRIBUTE, REMOVE_ATTRIBUTE};
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action == ADD_ATTRIBUTE) {
			if (sender.equals(this.tableRequiredAttributes)) {
				this.editAttribute(self.tableRequiredAttributes, null);
			} else {
				this.editAttribute(self.tableAttributes, null);
			}
			return;
		}
		if (action == EDIT_ATTRIBUTE) {
			assert(target instanceof ResolverAttribute);
			if (sender.equals(this.tableRequiredAttributes)) {
				this.editAttribute(self.tableRequiredAttributes, (ResolverAttribute) target);
			} else {
				this.editAttribute(self.tableAttributes, (ResolverAttribute) target);
			}
			return;
		}
		if (action == CLONE_ATTRIBUTE) {
			assert(target instanceof ResolverAttribute);
			try {
				//
				// Which table?
				//
				if (sender.equals(this.tableRequiredAttributes)) {
					//
					// Clone the attribute giving it a new
					// field name.
					//
					ResolverAttribute clone = new ResolverAttribute(this.fieldPrefix, this.getNextField(), (ResolverAttribute) target);
					//
					// Add it to the container
					//
					this.fieldsContainer.addBean(clone);
					//
					// Reset the page length so we can see it and have room
					// to add another.
					//
					this.tableRequiredAttributes.setPageLength(this.fieldsContainer.size() + 1);
					//
					// Select it
					//
					this.tableRequiredAttributes.select(clone);
				} else {
					//
					// Clone the attribute giving it a new
					// field name.
					//
					ResolverAttribute clone = new ResolverAttribute(this.parameterPrefix, this.getNextParameter(), (ResolverAttribute) target);
					//
					// Add it to the container
					//
					this.parametersContainer.addBean(clone);
					//
					// Reset the page length so we can see it and have room
					// to add another.
					//
					this.tableAttributes.setPageLength(this.parametersContainer.size() + 1);
					//
					// Select it
					//
					this.tableAttributes.select(clone);
				}
				//
				// We have changed
				//
				this.fireFormChangedEvent();
			} catch (Exception e) {
				logger.error("Failed to clone: " + e);
			}
			return;
		}
		if (action == REMOVE_ATTRIBUTE) {
			assert(target instanceof ResolverAttribute);
			//
			// Help method to remove the attribute
			//
			this.removeAttribute((ResolverAttribute) target);
			//
			// Which table?
			//
			if (sender.equals(this.tableRequiredAttributes)) {
				//
				// Now remove it from the table
				//
				this.tableRequiredAttributes.removeItem(target);
			} else {
				//
				// Now remove it from the table
				//
				this.tableAttributes.removeItem(target);
			}
			//
			// we have changed
			//
			this.fireFormChangedEvent();
			return;
		}
	}

	protected void removeAttribute(ResolverAttribute target) {
		this.entity.getEntity().removePipresolverParam(target.getIdParam());
		this.entity.getEntity().removePipresolverParam(target.getCategoryParam());
		this.entity.getEntity().removePipresolverParam(target.getDatatypeParam());
		this.entity.getEntity().removePipresolverParam(target.getIssuerParam());
	}

	protected void editAttribute(final Table table, final ResolverAttribute request) {
		if (this.isCSV()) {
			this.editCSVAttribute(table, request);
		} else {
			this.editResolverAttribute(table, request, null);
		}
	}
	
	protected void editCSVAttribute(final Table table, final ResolverAttribute request) {
		assert(this.isCSV());
		//
		// Prompt for the column
		//
		final ColumnSelectionWindow window = new ColumnSelectionWindow((request != null ? request.getColumn() : 0));
		if (request == null) {
			window.setCaption("Input the CSV Column for the new attribute");
		} else {
			window.setCaption("Edit the CSV Column for the attribute");
		}
		window.setModal(true);
		window.center();
		window.addCloseListener(new CloseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				//
				// Did the user save?
				//
				if (window.isSaved() == false) {
					return;
				}
				//
				// Save it if its not a new
				//
				if (request != null) {
					request.setColumn(window.getColumn());
				}
				//
				// Now we select the attribute, pass the column
				// in case this is a brand new attribute. Yeah its messy.
				//
				self.editResolverAttribute(table, request, window.getColumn());
			}
		});
		window.center();
		UI.getCurrent().addWindow(window);
	}

	protected void editResolverAttribute(final Table table, final ResolverAttribute request, final Integer column) {
		//
		// Provide objects to the attribute selection window
		//
		AttributeDesignatorType designator = new AttributeDesignatorType();
		if (request == null) {
			designator.setAttributeId(XACML3.ID_SUBJECT_SUBJECT_ID.stringValue());
			designator.setCategory(XACML3.ID_SUBJECT_CATEGORY_ACCESS_SUBJECT.stringValue());
			designator.setDataType(XACML3.ID_DATATYPE_STRING.stringValue());
			designator.setIssuer(this.entity.getEntity().getIssuer());
		} else {
			designator.setAttributeId(request.getId());
			designator.setCategory(request.getCategory());
			designator.setDataType(request.getDatatype());
			designator.setIssuer(request.getIssuer());
		}
		//
		// Have user select an attribute
		//
		final AttributeSelectionWindow selection = new AttributeSelectionWindow(null, designator);
		selection.setModal(true);
		selection.setCaption("Select Attribute");
		selection.addCloseListener(new CloseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent event) {
				//
				// User click Save button?
				//
				if (selection.isSaved() == false) {
					return;
				}
				//
				// Yes - get the final attribute
				//
				Attribute attribute = selection.getAttribute();
				//
				// Is it a new one?
				//
				if (request == null) {
					try {
						//
						// Create a new bean
						//
						ResolverAttribute bean = null;
						if (table.equals(self.tableRequiredAttributes)) {
							bean = new ResolverAttribute(self.fieldPrefix, self.getNextField());
						} else {
							bean = new ResolverAttribute(self.parameterPrefix, self.getNextParameter());
						}
						bean.setId(attribute.getXacmlId());
						bean.setCategory(attribute.getCategoryBean().getXacmlId());
						bean.setDatatype(attribute.getDatatypeBean().getXacmlId());
						if (attribute.getIssuer() != null) {
							bean.setIssuer(attribute.getIssuer());
						}
						if (column != null) {
							bean.setColumn(column);
						}
						//
						// Add it to the resolver
						//
						bean.setResolver(self.entity.getEntity());
						self.entity.getEntity().addPipresolverParam(bean.getIdParam());
						self.entity.getEntity().addPipresolverParam(bean.getCategoryParam());
						self.entity.getEntity().addPipresolverParam(bean.getDatatypeParam());
						if (bean.getIssuer() != null) {
							self.entity.getEntity().addPipresolverParam(bean.getIssuerParam());
						}
						//
						// Which table?
						//
						if (table.equals(self.tableRequiredAttributes)) {
							//
							// Add to table
							//
							self.fieldsContainer.addBean(bean);
							//
							// Reset the page length
							//
							self.tableRequiredAttributes.setPageLength(self.fieldsContainer.size() + 1);
						} else if (table.equals(self.tableAttributes)) {
							//
							// Add to table
							//
							self.parametersContainer.addBean(bean);
							//
							// Reset the page length
							//
							self.tableAttributes.setPageLength(self.parametersContainer.size() + 1);
						}
						if (logger.isDebugEnabled()) {
							logger.debug("Added new attribute: " + bean);
						}
					} catch (Exception e) {
						logger.error(e);
						AdminNotification.error("Unable to add another required attribute field");
					}
				} else {
					//
					// Update the table entry
					//
					request.setId(attribute.getXacmlId());
					request.setCategory(attribute.getCategoryBean().getXacmlId());
					request.setDatatype(attribute.getDatatypeBean().getXacmlId());
					request.setIssuer(attribute.getIssuer());
					//
					// Let the table know
					//
					table.refreshRowCache();
				}
				//
				// we have changed
				//
				self.fireFormChangedEvent();
			}
		});
		selection.center();
		UI.getCurrent().addWindow(selection);
	}
	
	protected String	getNextField() throws Exception {
		//
		// Start at the next index number. NOTE: The GUI needs
		// to use numbers to index the fields.
		//
		int index = this.tableRequiredAttributes.size() + 1;
		//
		// Really if we get to 100, that's an insane number of fields
		// needed for a SQL query.
		//
		while (index < 100) {
			String field = String.format("%02d", index);
			boolean exists = false;
			for (Object id : this.tableRequiredAttributes.getItemIds()) {
				@SuppressWarnings("unchecked")
				BeanItem<ResolverAttribute> required = (BeanItem<ResolverAttribute>) this.tableRequiredAttributes.getItem(id);
				if (required.getBean().getIdentifier().equals(field)) {
					exists = true;
					index++;
					break;
				}
			}
			if (exists == false) {
				return field;
			}
		}
		//
		// Sanity check
		//
		throw new Exception("Unable to find a next field name. Are there too many fields?");
	}
	
	protected String	getNextParameter() throws Exception {
		//
		// Start at the next index number. NOTE: The GUI needs
		// to use numbers to index the fields.
		//
		int index = this.tableAttributes.size() + 1;
		//
		// Really if we get to 100, that's an insane number of fields
		// needed for a SQL query.
		//
		while (index < 100) {
			String field = String.format("%02d", index);
			boolean exists = false;
			for (Object id : this.tableAttributes.getItemIds()) {
				@SuppressWarnings("unchecked")
				BeanItem<ResolverAttribute> required = (BeanItem<ResolverAttribute>) this.tableAttributes.getItem(id);
				if (required.getBean().getIdentifier().equals(field)) {
					exists = true;
					index++;
					break;
				}
			}
			if (exists == false) {
				return field;
			}
		}
		//
		// Sanity check
		//
		throw new Exception("Unable to find a next parameter name. Are there too many parameters?");
	}
	
	public void discard() throws SourceException {
		if (this.isSQL()) {
			this.textAreaSelect.discard();
		} else if (this.isLDAP()) {
			this.textFieldBase.discard();
			this.textFieldFilter.discard();
		} else if (this.isCSV()) {
		}
	}
	
	public void validate() throws InvalidValueException {
		if (this.isSQL()) {
			this.textAreaSelect.validate();
		} else if (this.isLDAP()) {
			this.textFieldBase.validate();
			this.textFieldFilter.validate();
		} else if (this.isCSV()) {
			
		}
	}
	
	public void commit() throws SourceException, InvalidValueException {
		//
		// Commit required fields
		//
		if (this.isSQL()) {
			this.textAreaSelect.commit();
		} else if (this.isLDAP()) {
			this.textFieldBase.commit();
			this.textFieldFilter.commit();
		} else if (this.isCSV()) {
		}
		//
		// Setup the fields
		//
		{
			List<String> fields = new ArrayList<String>(this.fieldsContainer.size());
			for (ResolverAttribute attribute : this.fieldsContainer.getItemIds()) {
				fields.add(attribute.getIdentifier());
			}
			PIPResolverParam param = (PIPResolverParam) this.tableRequiredAttributes.getData();
			if (param == null) {
				param = new PIPResolverParam();
				if (this.isSQL()) {
					param.setParamName("fields");
				} else if (this.isLDAP()) {
					param.setParamName("filter.view");
				} else if (this.isCSV()) {
					param.setParamName("fields");
				}
				this.entity.getEntity().addPipresolverParam(param);
				this.tableRequiredAttributes.setData(param);
			}
			param.setParamValue(Joiner.on(',').join(fields));
		}
		//
		// Setup the parameters
		//
		{
			List<String> parameters = new ArrayList<String>(this.parametersContainer.size());
			for (ResolverAttribute attribute : this.parametersContainer.getItemIds()) {
				parameters.add(attribute.getIdentifier());
			}
			PIPResolverParam param = (PIPResolverParam) this.tableAttributes.getData();
			if (param == null) {
				param = new PIPResolverParam();
				if (this.isSQL()) {
					param.setParamName("parameters");
				} else if (this.isLDAP()) {
					param.setParamName("filter.parameters");
				} else if (this.isCSV()) {
					param.setParamName("parameters");
				}
				this.entity.getEntity().addPipresolverParam(param);
				this.tableAttributes.setData(param);
			}
			param.setParamValue(Joiner.on(',').join(parameters));
		}
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	@Override
	public boolean addListener(FormChangedEventListener listener) {
		return this.notifier.addListener(listener);
	}

	@Override
	public boolean removeListener(FormChangedEventListener listener) {
		return this.notifier.removeListener(listener);
	}

	@Override
	public void fireFormChangedEvent() {
		this.notifier.fireFormChangedEvent();
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// textAreaSelect
		textAreaSelect = new TextArea();
		textAreaSelect.setCaption("SQL Select Statement");
		textAreaSelect.setImmediate(false);
		textAreaSelect.setWidth("100.0%");
		textAreaSelect.setHeight("-1px");
		textAreaSelect.setInvalidAllowed(false);
		textAreaSelect.setRequired(true);
		mainLayout.addComponent(textAreaSelect);
		mainLayout.setExpandRatio(textAreaSelect, 1.0f);
		
		// textFieldBase
		textFieldBase = new TextField();
		textFieldBase.setCaption("Base DN");
		textFieldBase.setImmediate(false);
		textFieldBase.setWidth("-1px");
		textFieldBase.setHeight("-1px");
		mainLayout.addComponent(textFieldBase);
		
		// textFieldFilter
		textFieldFilter = new TextField();
		textFieldFilter.setCaption("Filter");
		textFieldFilter.setImmediate(false);
		textFieldFilter.setWidth("-1px");
		textFieldFilter.setHeight("-1px");
		mainLayout.addComponent(textFieldFilter);
		
		// checkBoxShortIds
		checkBoxShortIds = new CheckBox();
		checkBoxShortIds.setCaption("Display short id’s.");
		checkBoxShortIds.setImmediate(false);
		checkBoxShortIds.setWidth("-1px");
		checkBoxShortIds.setHeight("-1px");
		mainLayout.addComponent(checkBoxShortIds);
		
		// tableRequiredAttributes
		tableRequiredAttributes = new Table();
		tableRequiredAttributes.setCaption("Attributes Returned");
		tableRequiredAttributes.setImmediate(false);
		tableRequiredAttributes.setWidth("-1px");
		tableRequiredAttributes.setHeight("-1px");
		mainLayout.addComponent(tableRequiredAttributes);
		
		// tableAttributes
		tableAttributes = new Table();
		tableAttributes.setCaption("Parameters - Attributes Needed (i.e. ?)");
		tableAttributes.setImmediate(false);
		tableAttributes.setWidth("-1px");
		tableAttributes.setHeight("-1px");
		tableAttributes.setInvalidAllowed(false);
		tableAttributes.setRequired(true);
		mainLayout.addComponent(tableAttributes);
		
		return mainLayout;
	}

}
