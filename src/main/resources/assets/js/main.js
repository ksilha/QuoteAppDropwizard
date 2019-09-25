// Models
window.Quote = Backbone.Model.extend({
    urlRoot:"api/quotes",
    defaults:{
        "id":null,
        "author":"",
        "quoteText":""
    }
});

window.QuoteCollection = Backbone.Collection.extend({
    model:Quote,
    url:"api/quotes"
});

// Views
window.QuoteListView = Backbone.View.extend({

    tagName:'ul',

    initialize:function () {
        this.model.bind("reset", this.render, this);
        var self = this;
        this.model.bind("add", function (quote) {
            $(self.el).append(new QuoteListItemView({model:quote}).render().el);
        });
    },

    render:function (eventName) {
        _.each(this.model.models, function (quote) {
            $(this.el).append(new QuoteListItemView({model:quote}).render().el);
        }, this);
        return this;
    }

});

window.QuoteListItemView = Backbone.View.extend({

    tagName:"li",

    template:_.template($('#tpl-quote-list-item').html()),
    
    initialize:function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
    
    close:function () {
        $(this.el).unbind();
        $(this.el).remove();
    }
});

window.QuoteView = Backbone.View.extend({

    template:_.template($('#tpl-quote-details').html()),
    
    initialize:function () {
        this.model.bind("change", this.render, this);
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
    
    events:{
        "change input":"change",
        "click .save":"saveQuote",
        "click .delete":"deleteQuote"
    },

    change:function (event) {
        var target = event.target;
        console.log('changing ' + target.id + ' from: ' + target.defaultValue + ' to: ' + target.value);
    },

    saveQuote:function () {
        this.model.set({
            author:$('#author').val(),
            quoteText:$('#quoteText').val()
        });
        if (this.model.isNew()) {
            var self = this;
            app.quoteList.create(this.model, {
                success:function () {
                    app.navigate('quotes/' + self.model.id, false);
                }
            });
        } else {
            this.model.save();
        }
        return false;
    },

    deleteQuote:function () {
        this.model.destroy({
            success:function () {
                alert('Quote deleted successfully');
                window.history.back();
            }
        });
        return false;
    },

    close:function () {
        $(this.el).unbind();
        $(this.el).empty();
    }
});

window.HeaderView = Backbone.View.extend({

    template:_.template($('#tpl-header').html()),

    initialize:function () {
        this.render();
    },

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    },

    events:{
        "click .new":"newQuote",
        "click .random":"randomQuote"
    },

    newQuote:function (event) {
        if (app.quoteView) app.quoteView.close();
        app.quoteView = new QuoteView({model:new Quote()});
        $('#content').html(app.quoteView.render().el);
        return false;
    },
    
    randomQuote:function (event) {
        window.location.href = '/';
    }
});

var RandomQuote = Backbone.Model.extend({
  
    urlRoot:'api/quotes/random',
});

window.QuoteOfTheDayView = Backbone.View.extend({
    
    template:_.template($('#tpl-quote-of-the-day').html()),

    initialize:function () {
        var _thisView = this;
        this.model.fetch().done(function () {
          _thisView.render();
        });
    },
    
    render:function (eventName) {
        console.log(this.model.toJSON());
        $('#dailyquote').html(this.$el.html(this.template(this.model.toJSON())));
    }
});


// Router
var AppRouter = Backbone.Router.extend({

    routes:{
        "":"list",
        "quotes/:id":"quoteDetails"
    },
    
    initialize:function () {
        $('#header').html(new HeaderView().render().el);
        var randomQuote = new RandomQuote();
        new QuoteOfTheDayView({ model: randomQuote });
    },

    list:function () {
        this.quoteList = new QuoteCollection();
        this.quoteListView = new QuoteListView({model:this.quoteList});
        this.quoteList.fetch();
        $('#sidebar').html(this.quoteListView.render().el);
    },

    quoteDetails:function (id) {
        this.quote = this.quoteList.get(id);
        if (app.quoteView) app.quoteView.close();
        this.quoteView = new QuoteView({model:this.quote});
        $('#content').html(this.quoteView.render().el);
    }
});

var app = new AppRouter();
Backbone.history.start();