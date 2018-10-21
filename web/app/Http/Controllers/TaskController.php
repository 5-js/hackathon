<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Auth;
use App\Traits\API;
use App\Task;
use App\Classroom;
use App\Http\Resources\Task as TaskResource;

class TaskController extends Controller
{
    use API;

    public function index($id)
    {
        return $this->respond([ 'tasks' => TaskResource::collection(Task::where('user_id',$id)->latest()->get())], 'ok');
    }

    public function store(Request $request)
    {
        
        $request->validate(Task::rules());

        if ($request->has('class_id')) {
            $class = Classroom::find($request->class_id);

            foreach($class->students as $student){
                $task = Task::create([
                    'title'    => $request->title, 
                    'content'  => $request->content, 
                    'due_date' => $request->due_date, 
                    'class_id' => $request->class_id, 
                    'user_id'  => $student->id
                ]);
            }
        } else {
            $task = Task::create([
                'title'    => $request->title, 
                'content'  => $request->content, 
                'due_date' => $request->due_date, 
                'class_id' => null, 
                'user_id'  => Auth::id()
            ]);
        }

        return $this->respond(['task' => new TaskResource($task)], 'created');
    }

    public function changeStatus($id, Request $request)
    {
        if (!$this->taskExists($id))
            return $this->respond([ 'message'  => 'Whoops, task not found!'], 'not_found');
        
        $check = Task::find($id);
        if ($check->class_id != null && $check->user_id == Auth::id())
            return $this->respond(['message' => 'You are not authorized to perform this action!'], 'forbidden');    

        Task::where('id',$id)->update([
            'status' => $request->status
        ]);
        
        return $this->respond(['message' => 'Task has been updated'], 'done');
    }

    public function show($id)
    {
        if ( !$this->taskExists($id) )
            return $this->respond([ 'message'  => 'Whoops, task not found!'], 'not_found');
        return $this->respond(['task' => new TaskResource(Task::find($id))], 'ok');
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

        return $this->respond([ 'message' => 'Task has been updated!'], 'ok');
    }

    public function destroy($id)
    {
        if ($this->taskExists($id))
            return $this->respond([ 'message' => 'Whoops, task is not found!'], 'not_found');
        
        Task::where('id', $id)->destroy();
        return $this->respond([ 'message' => 'Task has been deleted!'], 'ok');
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
