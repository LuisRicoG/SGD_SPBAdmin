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
      timeZone: 'America/Mexico_City',
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar
      buttonIcons: false, // show the prev/next text
      weekNumbers: true,
      navLinks: true, // can click day/week names to navigate views
      eventLimit: true, // allow "more" link when too many events
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Título del Evento:');
        if (title) {
            var data = {
            //groupId: null,   
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay === true ? 1 : 0,      
            className: 'gobcorp'
            };
            insertItem(data);
            $('#calendar').fullCalendar('refetchEvents');
            calendar.unselect();
            calendar.rerenderEvents();
        }        
      },
      drop: function(arg) {
        var data = {
            //groupId: null,   
            title: arg.draggedEl.innerText,
            start: arg.date,
            end: null,
            allDay: arg.allDay === true ? 1 : 0,
            className: 'gobcorp'
        };
        insertItem(data);
        $('#calendar').fullCalendar('refetchEvents');
        calendar.unselect();
        calendar.rerenderEvents();

        // is the "remove after drop" checkbox checked?
        if (document.getElementById('drop-remove').checked) {
          // if so, remove the element from the "Draggable Events" list
          arg.draggedEl.parentNode.removeChild(arg.draggedEl);
        }
      },
      eventDrop: function(eventDropInfo) { 
        var data = {
            id: eventDropInfo.oldEvent.id,
            //groupId: null,   
            title: eventDropInfo.event.title,
            start: eventDropInfo.event.start,
            end: eventDropInfo.event.end,
            allDay: eventDropInfo.event.allDay === true ? 1 : 0,
            className: 'gobcorp'
        };
        updateItem(data);
        $('#calendar').fullCalendar('refetchEvents');
        calendar.unselect();
        calendar.rerenderEvents();
      },
      eventResize: function(eventResizeInfo) { 
        var data = {
            id: eventResizeInfo.prevEvent.id,
            //groupId: null,   
            title: eventResizeInfo.event.title,
            start: eventResizeInfo.event.start,
            end: eventResizeInfo.event.end,
            allDay: eventResizeInfo.event.allDay === true ? 1 : 0,
            className: 'gobcorp'
        };
        updateItem(data);
        $('#calendar').fullCalendar('refetchEvents');
        calendar.unselect();
        calendar.rerenderEvents();
      },
      eventClick: function(info) {
        var data = {
            id: info.event.id,
            //groupId: info.event.groupId,   
            title: info.event.title,
            start: info.event.start,
            end: info.event.end,
            allDay: info.event.allDay,
            className: info.event.className
        };
        if (confirm("¿Esta seguro que desea borrar este evento?")) {
          deleteItem(data);        
          $('#calendar').fullCalendar('refetchEvents');
          calendar.unselect();
          calendar.rerenderEvents();
        }         
      },
      eventMouseEnter: function(mouseEnterInfo) {  
          mouseEnterInfo.el.style.borderColor = 'red';
      },
      eventMouseLeave: function(mouseLeaveInfo) { 
          mouseLeaveInfo.el.style.borderColor = 'white';
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
  
  function updateItem (item) {
        return $.ajax({
            type: "PUT",
            url: '/SGDADMIN/calendario/updateevento',
            dataType: 'json',            
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function(json) {
                $('#calendar').fullCalendar('refetchEventSources', '/SGDADMIN/calendario/listaeventos');
                return json;
            }
        });
    }
    
  function deleteItem (item) {
        return $.ajax({
            type: "PUT",
            url: '/SGDADMIN/calendario/deleteevento',
            dataType: 'json',            
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function(json) {
                $('#calendar').fullCalendar('refetchEventSources', '/SGDADMIN/calendario/listaeventos');
                return json;
            }
        });
    }