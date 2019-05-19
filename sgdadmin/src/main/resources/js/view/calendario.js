/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.addEventListener('DOMContentLoaded', function() {
    var initialLocaleCode = 'es';
    var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendarInteraction.Draggable

    /* initialize the external events
    -----------------------------------------------------------------*/

    var containerEl = document.getElementById('external-events-list');
    new Draggable(containerEl, {
      itemSelector: '.fc-event',
      eventData: function(eventEl) {
        return {
          title: eventEl.innerText.trim()
        }
      }
    });

    /* initialize the calendar
    -----------------------------------------------------------------*/

    var calendarEl = document.getElementById('calendar');
    var calendar = new Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listYear'
      },
      locale: initialLocaleCode,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar
      buttonIcons: false, // show the prev/next text
      weekNumbers: true,
      navLinks: true, // can click day/week names to navigate views
      eventLimit: true, // allow "more" link when too many events
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) {
            var data = {
            groupId: 1,   
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: 1,
            className: 'gobcorp'
            };
            insertItem(data);
            $('#calendar').fullCalendar('refetchEvents');
        }
        calendar.unselect();
      },
      drop: function(arg) {
        var data = {
            groupId: 1,   
            title: arg.title,
            start: arg.start,
            end: arg.end,
            allDay: 1,
            className: 'gobcorp'
        };
        insertItem(data);
        $('#calendar').fullCalendar('refetchEvents');
        calendar.unselect();
        // is the "remove after drop" checkbox checked?
        if (document.getElementById('drop-remove').checked) {
          // if so, remove the element from the "Draggable Events" list
          arg.draggedEl.parentNode.removeChild(arg.draggedEl);
        }
      },
      events: {
        url: '/SGDADMIN/calendario/listaeventos',
        type: 'POST',
        error: function() {
          alert('¡Hubo un error al cargar los eventos!');
        },
      }
    });
    
    calendar.render();

  });
  
  
  function insertItem (item) {
        return $.ajax({
            type: "POST",
            url: '/SGDADMIN/calendario/registroevento',
            dataType: 'json',            
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function(json) {
                $('#calendar').fullCalendar('refetchEventSources', '/SGDADMIN/calendario/listaeventos');
                return json;
            }
        });
    }
  