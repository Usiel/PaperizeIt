/**
 * 
 */

var paperize = {
		
		initPreferenceForm: function() {
			$('#preference-selection-form').submit(function (evt) {
				if ($('#topic-list input:checked').length == 0) {
					evt.preventDefault();
					alert("Please make sure that you selected at least 1 topic");
				} else {
					var sorted = $('#source-bias-like-container').sortable( "toArray" );
					var ignore = $('#source-bias-ignore-container').sortable( "toArray" );
					
					paperize.clearPostFields('preference-selection-form');
					
					$.each(sorted, function(key, value) {
						paperize.addPostFieldToForm('preference-selection-form', 'sorted', value);
					});
					
					$.each(ignore, function(key, value) {
						paperize.addPostFieldToForm('preference-selection-form', 'ignore', value);
					});
				}
			});
		},
		
		initSourceBias: function() {
			$('#source-bias-like-container, #source-bias-list, #source-bias-ignore-container').sortable({
				connectWith: ".connected-sortables"
			});
		},
		
		clearPostFields: function(formId) {
			$('#' + formId + ' #added-post-fields-container').remove();
			$('<div></div>').attr('id', 'added-post-fields-container').appendTo('#' + formId);
		},
		
		addPostFieldToForm: function(formId, name, value) {
			$('<input />').attr('type', 'hidden')
	          .attr('name', name)
	          .attr('value', value)
	          .appendTo('#' + formId + ' #added-post-fields-container');
		},
		
		initAutomaticPreferenceSelection: function () {
			$('#automatic-selection-button').click(function (evt) {
				evt.preventDefault();
				
				var form = $(this).closest('form');
				
				var valid = 1;
				
				form.find('input').each(function(index, element) {
					if (element.value == '') 
						valid = 0;
				});
				
				if (valid > 0) {
					$('#topic-list').find('input[type=checkbox]').each(function(index, element) {
						if (Math.floor(Math.random() * 2) > 0) {
							$(element).prop('checked', true);
						} else {
							$(element).prop('checked', false);
						}
						$(element).parent().parent().trigger('checkboxTreeRefresh');
					});
					
				}
			});
		},
		
	    checkTreeCheckbox: function (listElement) {
	        listElement.find('input:checkbox:first').prop('checked', true);
	        listElement.find('input:checkbox:first').prop('indeterminate', false);
	    },

	    uncheckTreeCheckbox: function (listElement) {
	        listElement.find('input:checkbox:first').prop('checked', false);
	        listElement.find('input:checkbox:first').prop('indeterminate', false);
	    },

	    // Checkbox is checked 'half' -> works only in newer browsers
	    halfcheckTreeCheckBox: function (listElement) {
	        listElement.find('input:checkbox:first').prop('checked', false);
	        listElement.find('input:checkbox:first').prop('indeterminate', true);
	    },

	    // Initializes (hierarchical) checkboxlists
	    initCheckboxes: function () {
	        // User clicked on a checkbox...
	        $('.checkboxlist input:checkbox').change(function (event) {
	            //Check all children for current item check status
	            $(this).parent().find('input:checkbox').prop('checked', $(this).prop('checked'));
	            //Trigger a refresh
	            $(this).parent().parent().trigger('checkboxTreeRefresh');
	        });

	        //Checkboxlist has to refresh:
	        $('ul.checkboxlist li').bind('checkboxTreeRefresh', function () {
	            //All checkboxes within the current branch
	            var allChildren = $(this).find('input:checkbox');
	            //Only checked checkboxes within the current branch
	            var checkedChildren = $(this).find('input:checked');

	            // -1 because we don't want to count the parent
	            var allChildrenCount = allChildren.length - 1;
	            // -1 OR 0 because we don't want to count the parent
	            var checkedChildrenCount = checkedChildren.length - $(this).children('input:checked').length;

	            if (allChildrenCount == checkedChildrenCount) { //All children are checked -> check current box
	                paperize.checkTreeCheckbox($(this));
	            } else if (checkedChildrenCount == 0) { //No child is checked -> uncheck current box
	                paperize.uncheckTreeCheckbox($(this));
	            } else if (checkedChildrenCount != allChildrenCount) { //Not all children are checked -> half-check current box
	                paperize.halfcheckTreeCheckBox($(this));
	            }
	        });

	        //Expand button for checkboxlist
	        $('ul.checkboxlist .expand-checkbox-button').click(function () {
	            //Selects the corresponding ul which contains all children for this checkbox
	            paperize.toggleCheckboxTreeBranch($(this).nextAll('ul.checkboxlist'));
	            //Change arrow direction to indicate open/close status
	            $(this).toggleClass('ui-icon-triangle-1-s');
	            $(this).toggleClass('ui-icon-triangle-1-e');
	        });
	    },

	    toggleCheckboxTree: function (tree) {
	        tree.find('.expand-checkbox-button').each(function (index, element) {
	            //Change arrow direction to indicate open/close status
	            $(this).toggleClass('ui-icon-triangle-1-s');
	            $(this).toggleClass('ui-icon-triangle-1-e');
	            xv.marketplace.toggleCheckboxTreeBranch($(element).nextAll('ul.checkboxlist'));
	        });
	    },

	    toggleCheckboxTreeBranch: function (childrenList) {
	        //Make list visible
	        childrenList.toggle();
	        //Disable children if they are invisible anyways UNLESS they are checked
	        childrenList.find('input[type=checkbox]').each(function () {
	            $(this).prop('disabled', !childrenList.is(':visible') && !$(this).is(':checked'));
	        });
	    },
}