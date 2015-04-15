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

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.RebaseResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.FetchResult;

import com.att.research.xacml.admin.XacmlAdminUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class GitSynchronizeWindow extends Window {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonSynchronize;
	@AutoGenerated
	private TextArea textAreaResults;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GitSynchronizeWindow self = this;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public GitSynchronizeWindow() {
		buildMainLayout();
		//setCompositionRoot(mainLayout);
		setContent(mainLayout);
		//
		// Set our shortcuts
		//
		this.setCloseShortcut(KeyCode.ESCAPE);
		//
		//
		//
		this.initializeButtons();
	}
	
	protected void initializeButtons() {
		this.buttonSynchronize.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (self.buttonSynchronize.getCaption().equals("Synchronize")) {
					self.synchronize();
				} else {
					self.close();
				}
			}			
		});
	}
	
	protected void synchronize() {
		//
		// Grab our working repository
		//
		Path repoPath = ((XacmlAdminUI)getUI()).getUserGitPath();
		try {
			final Git git = Git.open(repoPath.toFile());
			
			PullResult result = git.pull().call();
			FetchResult fetch = result.getFetchResult();
			MergeResult merge = result.getMergeResult();
			RebaseResult rebase = result.getRebaseResult();
			if (result.isSuccessful()) {
				//
				// TODO add more notification
				//
				this.textAreaResults.setValue("Successful!");
			} else {
				//
				// TODO
				//
				this.textAreaResults.setValue("Failed.");
			}
		} catch (IOException | GitAPIException e) {
			e.printStackTrace();
		}
		this.buttonSynchronize.setCaption("Ok");
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
		
		// textAreaResults
		textAreaResults = new TextArea();
		textAreaResults.setCaption("Synch Results");
		textAreaResults.setImmediate(false);
		textAreaResults.setWidth("462px");
		textAreaResults.setHeight("222px");
		mainLayout.addComponent(textAreaResults);
		
		// buttonSynchronize
		buttonSynchronize = new Button();
		buttonSynchronize.setCaption("Synchronize");
		buttonSynchronize.setImmediate(true);
		buttonSynchronize.setWidth("-1px");
		buttonSynchronize.setHeight("-1px");
		mainLayout.addComponent(buttonSynchronize);
		mainLayout.setComponentAlignment(buttonSynchronize, new Alignment(24));
		
		return mainLayout;
	}

}
