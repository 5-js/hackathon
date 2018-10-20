<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Auth;
use App\Traits\API;
use App\Task;
use App\Classroom;
class TaskController extends Controller
{
    use API;

    public function index($id)
    {
        return $this->respond([ 'tasks' => Task::where('user_id',$id)->latest()->get()], 'done');
    }

    public function store(Request $request)
    {
        
        $request->validate(Task::rules());

        return $this->respond(['task' => Task::create([
            'title'    => $request->title, 
            'content'  => $request->content, 
            'due_date' => $request->due_date, 
            'class_id' => $request->has('class_id')? $request->class_id : null, 
            'user_id'  => Auth::id()
        ])], 'created');
    }

    public function show($id)
    {
        if ( !$this->taskExists($id) )
            return $this->respond([ 'message'  => 'Whoops, task not found!'], 'not_found');
        return $this->respond(['task' => Task::find($id)], 'done');
    }

    public function update(Request $request, $id)
    {
        if ( $request->has('class_id') && !$this->classRoomExists($request->class_id)) 
            return $this->respond([ 'message' => 'Whoops, class is not found!'], 'not_found');

        if (!$this->taskExists($id))
            return $this->respond([ 'message' => 'Whoops, task is not found!'], 'not_found');

        $request->validate(Task::rule());

        Task::where('id',$id)->update([
            'title'    => $request->title, 
            'content'  => $request->content, 
            'due_date' => $request->due_date, 
            'class_id' => $request->has('class_id')? $request->class_id : null, 
            'user_id'  => Auth::id()
        ]);

        return $this->respond([ 'message' => 'Task has been updated!'], 'done');
    }

    public function destroy($id)
    {
        if ($this->taskExists($id))
            return $this->respond([ 'message' => 'Whoops, task is not found!'], 'not_found');
        
        Task::where('id', $id)->destroy();
        return $this->respond([ 'message' => 'Task has been deleted!'], 'done');
    }

    private function taskExists($id)
    {
        return Task::find($id)? true : false;
    }

    private function classRoomExists($id)
    {
        return Classroom::find($id)? true : false;
    }
}
